package fr.arrolla.trainreservation.domain;

public interface ServiceClient {
  void reset(String trainId);

  TrainData getTrainData(String trainId);

  TrainData makeReservation(Reservation reservation);

  String getNewBookingReference();
}
