namespace TicketOffice.Domain;

public interface IRestClient
{

    Task<string> GetBookingReference();
    Task<Train> GetTrain(string trainId);

    Task MakeReserveration(string trainId, string bookingReference, IEnumerable<string> seats);
}
