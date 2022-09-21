package fr.arrolla.trainreservation.domain;

public class NotEnoughFreeSeatsException extends RuntimeException {
  public NotEnoughFreeSeatsException() {
    super("Not enough free seats");
  }
}
