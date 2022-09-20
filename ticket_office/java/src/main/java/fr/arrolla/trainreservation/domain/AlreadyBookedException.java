package fr.arrolla.trainreservation.domain;

public class AlreadyBookedException extends RuntimeException {
  public AlreadyBookedException(String message) {
    super(message);
  }
}
