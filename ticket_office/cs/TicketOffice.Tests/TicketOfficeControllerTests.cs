using TicketOffice.Controllers;

namespace TicketOffice.Tests;

public class TicketOfficeControllerTests
{
    [Test]
    public async Task reserve_four_seats_from_empty_train()
    {
        const string trainId = "express_2000";
        var train = Helpers.MakeEmptyTrain();
        var fakeRestClient = new FakeRestClient(train);
        var bookingRequest = new BookingRequest(trainId, 4);
        var controller = new TicketOfficeController(fakeRestClient);

        var response = await controller.Reserve(bookingRequest);
    }

}
