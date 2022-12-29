namespace TicketOffice.Domain;

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
        return _seats.Values.ToList();
    }

    public void Add(Seat seat)
    {
        _seats[seat.Id] = seat;
    }

    public void Book(string seatId, string bookingReference)
    {
        var seat = GetSeat(seatId);
        seat.Book(bookingReference);
    }

    public Seat GetSeat(string id)
    {
        var res = _seats.GetValueOrDefault(id);
        if (res == null)
        {
            throw new NoSuchSeatException(id);
        }
        return res;
    }
}