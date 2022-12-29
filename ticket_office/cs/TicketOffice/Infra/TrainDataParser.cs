using Newtonsoft.Json.Linq;
using TicketOffice.Domain;

namespace TicketOffice.Infra;

public class TrainDataParser
{
    public static Train Parse(string json)
    {
        var train = new Train();

        var data = JObject.Parse(json);
        var jsonSeats = data["seats"].Values();
        foreach (var jsonSeat in jsonSeats)
        {
            var seatNumber = jsonSeat["seat_number"].Value<string>();
            var coachId = jsonSeat["coach"].Value<string>();
            var seatId = seatNumber + coachId;
            var seat = new Seat(seatId);
            var jsonBookingReference = jsonSeat["booking_reference"].Value<string>();
            if (!jsonBookingReference.Equals(""))
            {
                {
                    seat.Book(jsonBookingReference);
                }
            }
            train.Add(seat);

        }
        return train;
    }
}