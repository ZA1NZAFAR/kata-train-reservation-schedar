namespace TicketOffice.Domain;

public class Seat
{
    public string Id { get; }
    public string? BookingReference { get; private set; }

    public Seat(string id)
    {
        Id = id;
        BookingReference = null;
    }

    public Seat(string id, string bookingReference)
    {
        Id = id;
        BookingReference = bookingReference;
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
