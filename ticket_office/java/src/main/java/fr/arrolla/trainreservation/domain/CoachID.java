package fr.arrolla.trainreservation.domain;

public record CoachID(String coach) {
  
  public String toString() {
    return coach;
  }
}
