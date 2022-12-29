namespace TicketOffice.Tests;

public class AlreadyBookedException : Exception
{
    public AlreadyBookedException(string id, string oldReference, string newReference) :
        base($"Seat '{id}' is already booked with '{oldReference}', cannot book with '{newReference}'")
    { }
}