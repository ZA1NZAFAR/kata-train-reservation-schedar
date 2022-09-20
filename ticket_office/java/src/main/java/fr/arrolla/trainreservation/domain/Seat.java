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

  public Seat(SeatNumber number, CoachID coach) {
    this(number, coach, null);
  }

  public SeatID id() {
    return new SeatID(number, coach);
  }

  public void book(String bookingReference) {
    if (this.isFree()) {
      this.bookingReference = bookingReference;
    }

    if (this.bookingReference.equals(bookingReference)) {
      return;
    }
    
    String message = String.format("Seat '%s' already booked with reference '%s', but trying to book with '%s",
      this.number,
      this.bookingReference,
      bookingReference
    );

    throw new AlreadyBookedException(message);
  }

  public boolean isFree() {
    return this.bookingReference == null;
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
