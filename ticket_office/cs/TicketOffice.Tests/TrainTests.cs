namespace TicketOffice.Tests;

public class TrainTests
{
    [Test]
    public void all_seats_in_an_empty_train_are_free()
    {
        var train = Helpers.MakeEmptyTrain();
        foreach (var seat in train.Seats())
        {
            Assert.That(seat.IsFree(), Is.True);
        }

    }

    [Test]
    public void throws_no_such_seat()
    {
        var train = Helpers.MakeEmptyTrain();

        Assert.Throws<NoSuchSeatException>(() => train.GetSeat("X3"));
    }

    [Test]
    public void can_book_a_seat()
    {
        var train = Helpers.MakeEmptyTrain();
        train.Book("1A", "abc123");

        var seat = train.GetSeat("1A");
        Assert.That(seat.IsBooked(), Is.True);
        Assert.That(seat.BookingReference, Is.EqualTo("abc123"));
    }

    [Test]
    public void cannot_book_seat_twice_with_different_booking_references()
    {
        var train = Helpers.MakeEmptyTrain();
        train.Book("1A", "old-reference");

        Assert.Throws<AlreadyBookedException>(() => train.Book("1A", "new-reference"));
    }
}
