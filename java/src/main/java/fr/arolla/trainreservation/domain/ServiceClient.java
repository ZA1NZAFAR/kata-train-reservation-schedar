package fr.arolla.trainreservation.domain;

import fr.arolla.trainreservation.infra.Reservation;

public interface ServiceClient {
  String getBookingReference();

  Train getTrain(String trainId);

  void makeReservation(Reservation reservation);
}
