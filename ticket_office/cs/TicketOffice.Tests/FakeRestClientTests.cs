using TicketOffice.Domain;

namespace TicketOffice.Tests;

public class FakeRestClientTests
{
    private Train _train;
    private FakeRestClient _fakeRestClient;
    [SetUp]
    public void SetUp()
    {
        _train = Helpers.MakeEmptyTrain();
        _fakeRestClient = new FakeRestClient(_train);
    }

    [Test]
    public async Task return_different_booking_references_when_called()
    {
        var ref1 = await _fakeRestClient.GetBookingReference();
        var ref2 = await _fakeRestClient.GetBookingReference();

        Assert.That(ref1, Is.Not.EqualTo(ref2));
    }

    [Test]
    public void can_make_reservation()
    {
        _fakeRestClient.MakeReserveration("express_2000", "abc123", new[] { "1A", "2A" });

        var train = _fakeRestClient.Train;

        foreach (var seatId in new[] { "1A", "2A" })
        {
            var seat = train.GetSeat(seatId);
            Assert.That(seat.BookingReference!, Is.EqualTo("abc123"));
        }
    }

    [Test]
    public void throw_when_booking_conflict()
    {
        var train = _fakeRestClient.Train;

        train.Book("2A", "old-reference");

        Assert.Throws<AlreadyBookedException>(() =>
            _fakeRestClient.MakeReserveration("express_2000", "new-reference", new[] { "1A", "2A" })
        );
    }
}
