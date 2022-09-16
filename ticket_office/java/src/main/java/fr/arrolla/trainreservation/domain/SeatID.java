package fr.arrolla.trainreservation.domain;

public class SeatID {

  private final SeatNumber number;
  private final CoachID coach;

  public SeatID(SeatNumber number, CoachID coach) {
    this.number = number;
    this.coach = coach;
  }

  public String toString() {
    return this.number.toString() + this.coach.toString();
  }
}
