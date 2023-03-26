package fr.arolla.trainreservation.domain;

import fr.arolla.trainreservation.infra.Reservation;

import java.util.HashMap;
import java.util.List;

public class Train {
  private final HashMap<String, Seat> seats = new HashMap<>();

  public Train(List<Seat> seats) {
    for (var seat : seats) {
      this.seats.put(seat.id(), seat);
    }
  }

  public List<Seat> getSeats() {
    return seats.values().stream().toList();
  }

  public void book(Reservation reservation) {
    String bookingReference = reservation.bookingReference();
    List<String> seats = reservation.seats();
    for (var seatId : seats) {
      bookSeat(seatId, bookingReference);
    }
  }

  public void bookSeat(String seatId, String bookingReference) {
    var seat = this.seats.get(seatId);
    if (seat == null) {
      throw new NoSuchSeatException(seatId);
    }
    seat.book(bookingReference);
  }
}
