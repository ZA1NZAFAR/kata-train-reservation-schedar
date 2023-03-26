package fr.arolla.trainreservation.domain;

import java.util.Map;

public interface ServiceClient {
  String getBookingReference();

  Train getTrain(String trainId);

  void makeReservation(Map<String, Object> payload);
}
