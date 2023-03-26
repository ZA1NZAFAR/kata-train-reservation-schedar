package fr.arolla.trainreservation;

import java.util.Map;

public class FakeRestClient implements ServiceClient {
  @Override
  public String getBookingReference() {
    return "123abc";
  }

  @Override
  public String getTrainData(String trainId) {
    return "";
  }

  @Override
  public void makeReservation(Map<String, Object> payload) {

  }
}
