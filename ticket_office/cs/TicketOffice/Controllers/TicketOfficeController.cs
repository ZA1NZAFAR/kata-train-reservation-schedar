using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;
using TicketOffice.Domain;

namespace TicketOffice.Controllers;
public record BookingRequest(string train_id, int count) { }
public record BookingResponse(string booking_reference, List<string> seats) { }

[ApiController]
[Route("reserve")]
public class TicketOfficeController : ControllerBase
{
    private readonly IRestClient _restClient;

    public TicketOfficeController(IRestClient restClient)
    {
        _restClient = restClient;
    }

    [HttpPost(Name = "reserve")]
    public async Task<BookingResponse> Reserve(BookingRequest request)
    {

        var trainId = request.train_id;
        var count = request.count;

        // Step 1: Get a new booking reference from the 'booking_reference' service
        var bookingReference = await _restClient.GetBookingReference();

        // Step 2 : Get the train data from the 'train_data' service
        var json = await _restClient.GetTrainData(trainId);
        var data = JObject.Parse(json);
        var jsonSeats = data["seats"].Values();
        var availaibleSeats = new List<string>();
        foreach (var jsonSeat in jsonSeats)
        {
            var seatNumber = jsonSeat["seat_number"].Value<string>();
            var coachId = jsonSeat["coach"].Value<string>();
            var seatId = seatNumber + coachId;
            availaibleSeats.Add(seatId);
        }
        availaibleSeats.Sort();
        var seatsToBook = availaibleSeats.Take(count).ToList();

        // Step 3: make the reservation on the 'train data' servie
        await _restClient.MakeReserveration(trainId, bookingReference, seatsToBook);

        // Step 4: return the booking response
        var result = new BookingResponse(bookingReference, seatsToBook);
        return await Task.FromResult(result);
    }
}

