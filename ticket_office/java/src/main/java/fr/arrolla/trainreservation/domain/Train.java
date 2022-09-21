package fr.arrolla.trainreservation.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Train {
  private final HashMap<SeatID, Seat> seats = new HashMap<>();
  private final HashSet<CoachID> coaches = new HashSet<>();


  public Train(List<Seat> seats) {
    seats.forEach(seat -> {
      this.seats.put(seat.id(), seat);
      this.coaches.add(seat.coach());
    });
  }

  public Stream<Seat> seatsInCoach(CoachID coach) {
    return this.seats.values().stream().filter(seat -> seat.coach().equals(coach));
  }

  public Stream<Seat> seats() {
    return this.seats.values().stream();
  }

  public void reset() {
    seats.values().forEach(seat -> {
      seat.unBook();
    });
  }

  public void book(SeatID seatID, String bookingReference) {
    var seat = this.seats.get(seatID);
    if (seat == null) {
      String message = String.format("No seat with id '%s'", seatID.toString());
      throw new NoSuchSeatException(message);
    }
    seat.book(bookingReference);
  }

  public void applyReservation(Reservation reservation) {
    String bookingReference = reservation.bookingReference();
    reservation.seats().forEach(s -> book(s, bookingReference));
  }

  public double occupancyForCoach(CoachID coach) {
    var seats = seatsInCoach(coach).toList();
    var total = seats.size();
    var occupied = seats.stream().filter(Seat::isBooked).count();
    return occupied * 1.0f / total;
  }
}
