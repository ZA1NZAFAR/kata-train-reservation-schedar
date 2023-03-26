package fr.arolla.trainreservation.domain;

public record Seat(String number, String coach, String bookingReference) {
  public void book(String bookingReference) {
  }
}
