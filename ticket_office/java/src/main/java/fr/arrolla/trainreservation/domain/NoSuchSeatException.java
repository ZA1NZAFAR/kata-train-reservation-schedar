package fr.arrolla.trainreservation.domain;

public class NoSuchSeatException extends RuntimeException {
  public NoSuchSeatException(String message) {
    super(message);
  }
}
