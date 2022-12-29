namespace TicketOffice.Tests;

public class NoSuchSeatException : Exception
{
    public NoSuchSeatException(string id) :
        base($"No seat found with id {id}")
    { }
}