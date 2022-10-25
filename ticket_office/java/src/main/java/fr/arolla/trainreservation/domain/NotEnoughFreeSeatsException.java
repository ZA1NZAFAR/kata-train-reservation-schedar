package fr.arolla.trainreservation.domain;

public class NotEnoughFreeSeatsException extends RuntimeException {
  public NotEnoughFreeSeatsException() {
    super("Not enough free seats");
  }
}
