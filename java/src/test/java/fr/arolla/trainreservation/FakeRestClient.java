package fr.arolla.trainreservation;

import fr.arolla.trainreservation.domain.ServiceClient;
import fr.arolla.trainreservation.domain.Train;
import fr.arolla.trainreservation.infra.Reservation;

public class FakeRestClient implements ServiceClient {
  private final Train train;
  private int counter;

  public FakeRestClient(Train train) {
    this.train = train;
    this.counter = 0;
  }

  @Override
  public String getBookingReference() {
    counter++;
    return Integer.toString(counter);
  }

  @Override
  public Train getTrain(String trainId) {
    return this.train;
  }

  @Override
  public void makeReservation(Reservation reservation) {
    train.book(reservation);
  }
}
