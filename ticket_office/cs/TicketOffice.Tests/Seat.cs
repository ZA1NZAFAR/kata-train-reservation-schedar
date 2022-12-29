namespace TicketOffice.Tests;

public class Seat
{
    public string? BookingReference { get; private set; }
    public string Id { get; }

    public Seat(string id)
    {
        Id = id;
    }

    public bool IsBooked() => BookingReference != null;
    public bool IsFree() => BookingReference == null;

    public void Book(string bookingReference)
    {
        var oldReference = BookingReference;
        if (oldReference != null && !oldReference.Equals(bookingReference))
        {
            throw new AlreadyBookedException(Id, oldReference, bookingReference);
        }
        BookingReference = bookingReference;
    }
}
