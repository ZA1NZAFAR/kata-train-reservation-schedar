package fr.arolla.trainreservation.ticket_office.domain;

public class Seat implements Comparable<Seat> {

  private final SeatID id;
  private String bookingReference;
  private Seat(SeatID id, String bookingReference) {
    this.id = id;
    this.bookingReference = bookingReference;
  }
  public static Seat free(SeatID id) {
    return new Seat(id, null);
  }

  public static Seat booked(SeatID id, String bookingReference) {
    return new Seat(id, bookingReference);
  }

  public void book(String bookingReference) {
    if (this.isFree()) {
      this.bookingReference = bookingReference;
    }

    if (this.bookingReference.equals(bookingReference)) {
      return;
    }

    String message = String.format("Seat '%s' already booked with reference '%s', but trying to book with '%s",
      this.id,
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
    return this.id;
  }

  public SeatNumber number() {
    return id.number();
  }

  public CoachID coach() {
    return id.coach();
  }

  public String bookingReference() {
    return bookingReference;
  }

  @Override
  public int compareTo(Seat other) {
    return this.id().compareTo(other.id());
  }

}
