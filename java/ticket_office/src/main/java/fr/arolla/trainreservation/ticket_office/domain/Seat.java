package fr.arolla.trainreservation.ticket_office.domain;

public class Seat implements Comparable<Seat> {
  private final SeatNumber number;
  private final CoachID coach;
  private String bookingReference;

  public Seat(SeatNumber number, CoachID coach) {
    this(number, coach, null);
  }

  public Seat(SeatNumber number, CoachID coach, String bookingReference) {
    this.number = number;
    this.coach = coach;
    this.bookingReference = bookingReference;
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

  public void unBook() {
    this.bookingReference = null;
  }

  public boolean isFree() {
    return this.bookingReference == null;
  }

  public boolean isBooked() {
    return !this.isFree();
  }

  public SeatID id() {
    return new SeatID(number, coach);
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

  @Override
  public int compareTo(Seat other) {
    return this.id().compareTo(other.id());
  }

}
