package fr.arrolla.trainreservation.domain;

import java.util.ArrayList;
import java.util.stream.Stream;

public class SeatFinder {
  private final Train train;

  public SeatFinder(Train train) {
    this.train = train;
  }

  public Stream<SeatID> findSeats(int seatCount) {
    var toReserve = new ArrayList<SeatID>();
    var coach = findBestCoach(seatCount);
    if (coach == null) {
      throw new NotEnoughFreeSeatsException();
    }

    var inCoach = train.seatsInCoach(coach);
    var availableSeatsInCoach = inCoach.filter(Seat::isFree).sorted().toList();
    for (int i = 0; i < seatCount; i++) {
      toReserve.add(availableSeatsInCoach.get(i).id());
    }

    return toReserve.stream();
  }

  private CoachID findBestCoach(int seatCount) {
    for (var coach : train.getCoaches()) {
      if (train.occupancyForCoachAfterBooking(coach, seatCount) <= 0.7) {
        return coach;
      }
    }
    return null;
  }

}
