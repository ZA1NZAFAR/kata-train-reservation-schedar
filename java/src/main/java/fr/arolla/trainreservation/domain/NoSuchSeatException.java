package fr.arolla.trainreservation.domain;

public class NoSuchSeatException extends RuntimeException {
  private final String seatId;

  public NoSuchSeatException(String seatId) {
    super(String.format("No such seat :%s", seatId));
    this.seatId = seatId;
  }
}
