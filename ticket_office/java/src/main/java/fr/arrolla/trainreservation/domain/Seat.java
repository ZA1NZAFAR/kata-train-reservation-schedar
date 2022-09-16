package fr.arrolla.trainreservation.domain;

public record Seat(SeatNumber number, CoachID coach, String bookingReference) {

  public SeatID id() {
    return new SeatID(number, coach);
  }

}
