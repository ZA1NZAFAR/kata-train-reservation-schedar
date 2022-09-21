package fr.arrolla.trainreservation.domain;

import java.util.ArrayList;
import java.util.stream.Stream;

public class SeatFinder {
  private final Train train;

  public SeatFinder(Train train) {
    this.train = train;
  }

  public Stream<SeatID> findSeats(int seatCount) {
    var inFirstCoach = train.seatsInCoach(new CoachID("A"));
    var availableSeats = inFirstCoach.filter(Seat::isFree).sorted().toList();

    var seats = new ArrayList<SeatID>();
    for (int i = 0; i < seatCount; i++) {
      var availableSeat = availableSeats.get(i);
      seats.add(availableSeat.id());
    }
    return seats.stream();
  }

}
