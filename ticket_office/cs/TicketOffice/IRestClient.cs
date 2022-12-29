namespace TicketOffice;

public interface IRestClient
{

    Task<string> GetBookingReference();
    Task<string> GetTrainData(string trainId);

    Task MakeReserveration(string trainId, string bookingReference, IEnumerable<string> seats);
}
