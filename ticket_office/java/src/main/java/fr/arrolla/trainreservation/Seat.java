package fr.arrolla.trainreservation;

public record Seat(SeatNumber number, CoachID coach, String bookingReference) {

  public SeatID id() {
    return new SeatID(number, coach);
  }

}
