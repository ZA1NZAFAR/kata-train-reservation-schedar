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

    public Task<string> GetTrainData(string trainId)
    {
        return Task.FromResult("");
    }


    public Task MakeReserveration(string trainId, string bookingReference, IEnumerable<string> seats)
    {
        // Todo: make it fail when there's a reservation conflict
        return Task.CompletedTask;
    }
}
