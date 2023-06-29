package fr.arolla.trainreservation.ticket_office.domain;

public class AlreadyBookedException extends RuntimeException {
  public AlreadyBookedException(String message) {
    super(message);
  }
}
