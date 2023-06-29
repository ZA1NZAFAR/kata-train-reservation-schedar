package fr.arolla.trainreservation.ticket_office.domain;

public record SeatNumber(String number) {

  public String toString() {
    return number;
  }
}
