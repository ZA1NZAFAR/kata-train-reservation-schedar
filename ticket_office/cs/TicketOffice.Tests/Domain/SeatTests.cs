using TicketOffice.Domain;

namespace TicketOffice.Tests.Domain;

public class SeatTests
{
    [Test]
    public void sorting_seats()
    {
        var seat1 = new Seat("A1");
        var seat2 = new Seat("A2");
        var seat3 = new Seat("A3");

        var seats = new List<Seat> { seat3, seat1, seat2 };
        seats.Sort();

        Assert.That(seats, Is.EqualTo(new List<Seat> { seat1, seat2, seat3 }));
    }
}
