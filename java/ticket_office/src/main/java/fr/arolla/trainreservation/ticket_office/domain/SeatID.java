package fr.arolla.trainreservation.ticket_office.domain;

import org.jetbrains.annotations.NotNull;

public class SeatID implements Comparable<SeatID> {

  private final SeatNumber number;
  private final CoachID coach;

  public SeatID(SeatNumber number, CoachID coach) {
    this.number = number;
    this.coach = coach;
  }

  public SeatNumber getNumber() {
    return number;
  }

  public CoachID getCoach() {
    return coach;
  }

  public static SeatID parse(String s) {
    var numberString = s.substring(0, 1);
    var coachString = s.substring(1, 2);
    var number = new SeatNumber(numberString);
    var coach = new CoachID(coachString);

    return new SeatID(number, coach);
  }

  public String toString() {
    return this.number.toString() + this.coach.toString();
  }

  @Override
  public int compareTo(@NotNull SeatID other) {
    return this.toString().compareTo(other.toString());
  }


  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SeatID other)) {
      return false;
    }

    return other.toString().equals(this.toString());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
