namespace TicketOffice.Tests;

public class Helpers
{
    // Note: an empty train is *not* a train with no seats
    // It's a train where all seats are free
    public static Train MakeEmptyTrain()
    {
        var train = new Train();

        foreach (var coach in "ABCDEF")
        {
            for (var id = 1; id < 10; id++)
            {
                var seat = new Seat($"{id}{coach}");
                train.Add(seat);
            }
        }
        return train;
    }
}