package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.CoachID;
import fr.arrolla.trainreservation.domain.Seat;
import fr.arrolla.trainreservation.domain.SeatNumber;
import fr.arrolla.trainreservation.domain.TrainData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Helpers {
  static TrainData makeEmptyTrain() {
    var seats = new ArrayList<Seat>();
    var letters = new String[]{"A", "B", "C", "D", "E"};
    var coachIds = Arrays.stream(letters).map(s -> new CoachID(s));
    coachIds.forEach(coachID -> {
      var numbers = IntStream.range(1, 11);
      var seatsNumbers = numbers.mapToObj(i -> new SeatNumber(Integer.toString(i)));
      seatsNumbers.forEach(seatNumber -> {
        var seat = new Seat(seatNumber, coachID, null);
        seats.add(seat);
      });
    });

    return new TrainData(seats);
  }
}
