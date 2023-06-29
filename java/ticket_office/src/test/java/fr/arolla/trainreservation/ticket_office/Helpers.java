package fr.arolla.trainreservation.ticket_office;

import fr.arolla.trainreservation.ticket_office.domain.Seat;
import fr.arolla.trainreservation.ticket_office.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Helpers {
  public static Train makeEmptyTrain() {
    var seats = new ArrayList<fr.arolla.trainreservation.ticket_office.domain.Seat>();
    var letters = new String[]{"A", "B", "C", "D", "E"};
    var coachIDs = Arrays.stream(letters).map(s -> new CoachID(s));
    coachIDs.forEach(coachID -> {
      var numbers = IntStream.range(0, 10);
      var seatsNumbers = numbers.mapToObj(i -> new SeatNumber(Integer.toString(i)));
      seatsNumbers.forEach(seatNumber -> {
        var seat = new Seat(seatNumber, coachID, null);
        seats.add(seat);
      });
    });

    return new Train("express_2000", seats);
  }

  public static Train trainWithBookedSeats(Stream<String> booked) {
    var train = makeEmptyTrain();
    var seatIDs = booked.map(s -> SeatID.parse(s));
    seatIDs.forEach(seatID -> {
      train.book(seatID, "old");
    });
    return train;
  }
}
