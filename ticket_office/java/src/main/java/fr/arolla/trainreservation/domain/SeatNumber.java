package fr.arolla.trainreservation.domain;

public record SeatNumber(String number) {

  public String toString() {
    return number;
  }
}
