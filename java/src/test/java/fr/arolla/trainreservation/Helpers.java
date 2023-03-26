package fr.arolla.trainreservation;

import fr.arolla.trainreservation.domain.Seat;
import fr.arolla.trainreservation.domain.Train;

import java.util.ArrayList;

public class Helpers {
  public static Train makeEmptyTrain() {
    var seats = new ArrayList<Seat>();
    for (var c : "ABCDEF".toCharArray()) {
      for (int i = 1; i <= 10; i++) {
        var seat = new Seat(Integer.toString(i), Character.toString(c), "");
        seats.add(seat);
      }
    }
    return new Train(seats);
  }
}
