package fr.arolla.trainreservation.ticket_office;

import fr.arolla.trainreservation.ticket_office.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Helpers {
  public static Train makeEmptyTrain() {
    var seats = new ArrayList<fr.arolla.trainreservation.ticket_office.domain.Seat>();
    var letters = new String[]{"A", "B", "C", "D", "E"};
    var coachIds = Arrays.stream(letters);
    coachIds.forEach(coachId -> {
      var numbers = IntStream.range(0, 10);
      var seatsNumbers = numbers.mapToObj(i -> Integer.toString(i));
      seatsNumbers.forEach(seatNumber -> {
        var seatId = new SeatId(new SeatNumber(seatNumber), new CoachId(coachId));
        var seat = Seat.free(seatId);
        seats.add(seat);
      });
    });

    return new Train("express_2000", seats);
  }

  public static Train trainWithBookedSeats(Stream<String> booked) {
    var train = makeEmptyTrain();
    var seatIds = booked.map(s -> SeatId.parse(s));
    seatIds.forEach(seatId -> {
      train.book(seatId, "old");
    });
    return train;
  }
}
