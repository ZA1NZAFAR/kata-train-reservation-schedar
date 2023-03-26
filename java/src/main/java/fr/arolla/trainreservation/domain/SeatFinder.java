package fr.arolla.trainreservation.domain;

import java.util.stream.Stream;

public class SeatFinder {
  public static Stream<String> findSeats(Stream<Seat> seats, int seatCount) {
    var availableSeats = seats.filter(seat -> seat.bookingReference() == null);
    return availableSeats.limit(seatCount).map(Seat::id);
  }
}
