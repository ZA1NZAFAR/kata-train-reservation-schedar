namespace TicketOffice.Tests;

// Note: no ID - we are not in the same bounded context
// as train_data
public class Train
{
    private Dictionary<string, Seat> _seats;

    public Train()
    {
        _seats = new Dictionary<string, Seat>();
    }
    public List<Seat> Seats()
    {
        return new List<Seat>();
    }

    internal void Add(Seat seat)
    {
        _seats[seat.Id] = seat;
    }

    internal void Book(string seatId, string bookingReference)
    {
        var seat = GetSeat(seatId);
        seat.Book(bookingReference);
    }

    internal Seat GetSeat(string id)
    {
        var res = _seats.GetValueOrDefault(id);
        if (res == null)
        {
            throw new NoSuchSeatException(id);
        }
        return res;
    }
}