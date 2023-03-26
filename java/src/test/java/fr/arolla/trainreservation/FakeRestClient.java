package fr.arolla.trainreservation;

import java.util.ArrayList;
import java.util.Map;

public class FakeRestClient implements ServiceClient {
  @Override
  public String getBookingReference() {
    return "123abc";
  }

  @Override
  public Train getTrain(String trainId) {
    return new Train(new ArrayList<>());
  }

  @Override
  public void makeReservation(Map<String, Object> payload) {

  }
}
