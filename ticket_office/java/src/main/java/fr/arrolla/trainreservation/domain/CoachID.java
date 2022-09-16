package fr.arrolla.trainreservation.domain;

public class CoachID {
  private final String coach;

  public CoachID(String coach) {
    this.coach = coach;
  }

  public String toString() {
    return coach;
  }
}
