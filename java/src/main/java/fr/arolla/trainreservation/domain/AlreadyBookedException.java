package fr.arolla.trainreservation.domain;

public class AlreadyBookedException extends RuntimeException {
  public AlreadyBookedException(String oldBookingReference, String newBookingReference) {
    super(String.format("Seat already booked with %s, cannot book with", oldBookingReference, newBookingReference));
  }
}
