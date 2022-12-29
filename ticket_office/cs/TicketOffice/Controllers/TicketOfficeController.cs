using Microsoft.AspNetCore.Mvc;
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

        var bookingReference = await _restClient.GetBookingReference();

        var train = await _restClient.GetTrain(trainId);

        var seatFinder = new SeatFinder(train.Seats());
        var foundSeats = seatFinder.Find(count);
        var ids = foundSeats.Select(s => s.Id).ToList();

        await _restClient.MakeReserveration(trainId, bookingReference, ids);

        var result = new BookingResponse(bookingReference, ids);
        return await Task.FromResult(result);
    }
}

