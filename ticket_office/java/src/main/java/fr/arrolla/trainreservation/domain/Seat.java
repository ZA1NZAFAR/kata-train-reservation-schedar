package fr.arrolla.trainreservation.domain;

public class Seat {
  private final SeatNumber number;
  private final CoachID coach;
  private String bookingReference;

  public Seat(SeatNumber number, CoachID coach, String bookingReference) {
    this.number = number;
    this.coach = coach;
    this.bookingReference = bookingReference;
  }

  public SeatID id() {
    return new SeatID(number, coach);
  }

  public void setBookingReference(String bookingReference) {
    this.bookingReference = bookingReference;
  }

  public SeatNumber number() {
    return number;
  }

  public CoachID coach() {
    return coach;
  }

  public String bookingReference() {
    return bookingReference;
  }
}
