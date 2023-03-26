package fr.arolla.trainreservation.domain;

import java.util.Objects;

public final class Seat {
  private final String number;
  private final String coach;
  private String bookingReference;

  public Seat(String number, String coach, String bookingReference) {
    this.number = number;
    this.coach = coach;
    this.bookingReference = bookingReference;
  }

  public void book(String bookingReference) {
    var oldBookingReference = this.bookingReference;
    if (!oldBookingReference.equals("") && oldBookingReference != bookingReference) {
      throw new AlreadyBookedException(oldBookingReference, bookingReference);
    }
    this.bookingReference = bookingReference;
  }

  public String number() {
    return number;
  }

  public String coach() {
    return coach;
  }

  public String id() {
    return this.number + this.coach;
  }

  public String bookingReference() {
    return bookingReference;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Seat) obj;
    return Objects.equals(this.number, that.number) &&
      Objects.equals(this.coach, that.coach) &&
      Objects.equals(this.bookingReference, that.bookingReference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, coach, bookingReference);
  }

  @Override
  public String toString() {
    return "Seat[" +
      "number=" + number + ", " +
      "coach=" + coach + ", " +
      "bookingReference=" + bookingReference + ']';
  }

}
