namespace TicketOffice.Tests;

public class FakeRestClientTests
{
    private Train _train;
    private FakeRestClient _fakeRestClient;
    [SetUp]
    public void SetUp()
    {
        _train = new Train();
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
    public void return_configured_train()
    {
        var returnedTrain = _fakeRestClient.Train;

        Assert.That(returnedTrain.Seats(), Is.EqualTo(_train.Seats()));
    }
}
