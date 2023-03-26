package fr.arolla.trainreservation;

import fr.arolla.trainreservation.domain.ServiceClient;
import fr.arolla.trainreservation.domain.Train;
import fr.arolla.trainreservation.infra.Reservation;

import java.util.ArrayList;

public class FakeRestClient implements ServiceClient {
  private final Train train;

  public FakeRestClient(Train train) {
    this.train = train;
  }

  @Override
  public String getBookingReference() {
    return "123abc";
  }

  @Override
  public Train getTrain(String trainId) {
    return new Train(new ArrayList<>());
  }

  @Override
  public void makeReservation(Reservation reservation) {

  }
}
