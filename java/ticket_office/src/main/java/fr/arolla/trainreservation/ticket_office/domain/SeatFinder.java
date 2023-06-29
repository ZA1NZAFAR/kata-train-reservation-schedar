package fr.arolla.trainreservation.ticket_office.domain;

import java.util.List;

public class SeatFinder {
  private final Train train;

  public SeatFinder(Train train) {
    this.train = train;
  }

  public List<SeatID> findSeats(int seatCount) {
    var coach = findBestCoach(seatCount);
    var inCoach = train.seatsInCoach(coach);
    var availableSeatsInCoach = inCoach.filter(Seat::isFree).sorted();
    var toBook = availableSeatsInCoach.limit(seatCount);
    return toBook.map(s -> s.id()).sorted().toList();
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
