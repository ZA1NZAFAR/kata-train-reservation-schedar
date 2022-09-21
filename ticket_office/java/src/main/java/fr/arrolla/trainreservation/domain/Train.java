package fr.arrolla.trainreservation.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Train {
  private final String id;

  private final HashMap<SeatID, Seat> seats = new HashMap<>();
  private final HashSet<CoachID> coaches = new HashSet<>();


  public Train(String id, List<Seat> seats) {
    this.id = id;

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
    return occupied * 1.0 / total;
  }

  public List<CoachID> getCoaches() {
    return coaches.stream().toList();
  }

  public double occupancyForCoachAfterBooking(CoachID coach, int seatCount) {
    var seats = seatsInCoach(coach).toList();
    var total = seats.size();
    var occupied = seats.stream().filter(Seat::isBooked).count() + seatCount;
    return occupied * 1.0 / total;
  }

  @Override
  public String toString() {
    String res = "";
    for (var coach : coaches.stream().sorted().toList()) {
      for (var seat : seatsInCoach(coach).sorted().toList()) {
        var seatID = seat.id().toString();
        res += seatID;
        if (seat.isBooked()) {
          res += " " + seat.bookingReference();
        }
        res += "\n";
      }
    }
    return res;
  }
}
