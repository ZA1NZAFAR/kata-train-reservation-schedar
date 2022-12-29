namespace TicketOffice.Domain;

public class Seat : IComparable<Seat?>
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

    public override bool Equals(object? obj)
    {
        return Equals(obj as Seat);
    }

    public bool Equals(Seat? other)
    {
        return other is not null &&
               Id == other.Id &&
               BookingReference == other.BookingReference;
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(Id, BookingReference);
    }

    public int CompareTo(Seat? other)
    {
        // No idea if this is correct
        if (other == null) { return 1; };
        return this.Id.CompareTo(other.Id);
    }
}
