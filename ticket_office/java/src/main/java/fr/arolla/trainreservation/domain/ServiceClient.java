package fr.arolla.trainreservation.domain;

public interface ServiceClient {
  void reset(String trainId);

  Train getTrain(String trainId);

  Train makeReservation(Reservation reservation);

  String getNewBookingReference();
}
