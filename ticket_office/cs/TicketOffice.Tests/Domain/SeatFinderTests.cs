using TicketOffice.Domain;

namespace TicketOffice.Tests.Domain;

public class SeatFinderTests
{
    private Dictionary<string, Seat> _seats;

    [SetUp]
    public void SetUp()
    {
        _seats = new Dictionary<string, Seat>();
        foreach (var coach in "ABCDEF")
        {
            for (var number = 1; number < 10; number++)
            {
                string id = $"{coach}{number}";
                var seat = new Seat(id);
                _seats.Add(id, seat);
            }
        }
    }

    public void EnsureBooked(params string[] ids)
    {
        foreach (var id in ids)
        {
            _seats[id].Book("abc123");
        }

    }

    void Check(int count, params string[] expectedIds)
    {
        var seatFinder = new SeatFinder(_seats.Values);
        var actualIds = seatFinder.Find(count).Select(s => s.Id);
        Assert.That(actualIds, Is.EquivalentTo(expectedIds));
    }

    [Test]
    public void finds_four_seats_in_empty_train()
    {
        Check(4, "A1", "A2", "A3", "A4");
    }

    [Test]
    public void finds_four_additional_seats()
    {
        EnsureBooked("A1", "A2", "A3", "A4");

        Check(4, "A5", "A6", "A7", "A8");
    }
}