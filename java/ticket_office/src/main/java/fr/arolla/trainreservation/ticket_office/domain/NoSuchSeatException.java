package fr.arolla.trainreservation.ticket_office.domain;

public class NoSuchSeatException extends RuntimeException {
  public NoSuchSeatException(String message) {
    super(message);
  }
}
