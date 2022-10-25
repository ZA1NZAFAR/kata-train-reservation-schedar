package fr.arolla.trainreservation.domain;

import java.util.stream.Stream;

public class SeatFinder {
  private final Train train;

  public SeatFinder(Train train) {
    this.train = train;
  }

  public Stream<SeatID> findSeats(int seatCount) {
    var coach = findBestCoach(seatCount);
    var inCoach = train.seatsInCoach(coach);
    var availableSeatsInCoach = inCoach.filter(Seat::isFree).sorted();
    var toBook = availableSeatsInCoach.limit(seatCount);
    return toBook.map(s -> s.id());
  }

  private CoachID findBestCoach(int seatCount) {
    for (var coach : train.getCoaches()) {
      if (train.occupancyForCoachAfterBooking(coach, seatCount) <= 0.7) {
        return coach;
      }
    }
    throw new NotEnoughFreeSeatsException();
  }

}
