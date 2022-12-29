using TicketOffice.Infra;

namespace TicketOffice.Tests.Infra;

public class TrainDataParserTests
{
    [Test]
    public void parsing_train_data()
    {
        string trainDataJson = """    
        {
           "seats": {
              "1A": {
                "booking_reference": "",
                  "coach": "A",
                  "seat_number": "1"
              },
              "1B": {
                "booking_reference": "abc123",
                  "coach": "B",
                  "seat_number": "1"
              }
            }
        }
        """;

        var train = TrainDataParser.Parse(trainDataJson);
        var seat1 = train.GetSeat("1A");
        Assert.That(seat1.IsFree());
        var seat2 = train.GetSeat("1B");
        Assert.That(seat2.BookingReference!, Is.EqualTo("abc123"));
    }

}