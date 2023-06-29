package fr.arolla.trainreservation.ticket_office.domain;

public class NotEnoughFreeSeatsException extends RuntimeException {
  public NotEnoughFreeSeatsException() {
    super("Not enough free seats");
  }
}
