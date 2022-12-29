namespace TicketOffice.Domain;

public class SeatFinder
{
    private List<Seat> _seats;

    public SeatFinder(IEnumerable<Seat> seats)
    {
        _seats = seats.ToList();
    }

    public List<Seat> Find(int count)
    {
        var availaibleSeats = new List<Seat>();
        foreach (var seat in _seats)
        {
            if (seat.IsFree())
            {
                availaibleSeats.Add(seat);
            }
        }
        return availaibleSeats.Take(count).ToList();
    }
}