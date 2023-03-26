package fr.arolla.trainreservation;

import java.util.List;

public class Train {
  private final List<Seat> seats;

  public Train(List<Seat> seats) {
    this.seats = seats;
  }

  public List<Seat> getSeats() {
    return seats;
  }
}
