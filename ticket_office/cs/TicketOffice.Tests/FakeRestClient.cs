using TicketOffice.Domain;

namespace TicketOffice.Tests;

public class FakeRestClient : IRestClient
{
    private int _count;
    public Train Train { get; }

    public FakeRestClient(Train train)
    {
        _count = 0;
        Train = train;
    }


    public Task<string> GetBookingReference()
    {
        _count++;
        return Task.FromResult(_count.ToString());
    }

    public Task MakeReserveration(string trainId, string bookingReference, IEnumerable<string> seats)
    {
        foreach (var seat in seats)
        {
            Train.Book(seat, bookingReference);
        }
        return Task.CompletedTask;
    }

    public Task<Train> GetTrain(string trainId) => Task.FromResult(Train);

}
