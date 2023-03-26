package fr.arolla.trainreservation;

import java.util.Map;

public interface ServiceClient {
  String getBookingReference();

  String getTrainData(String trainId);

  void makeReservation(Map<String, Object> payload);
}
