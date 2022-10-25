package fr.arolla.trainreservation;

import fr.arolla.trainreservation.domain.ServiceClient;
import fr.arolla.trainreservation.domain.Train;
import fr.arolla.trainreservation.domain.Reservation;

public class FakeServiceClient implements ServiceClient {
  private Train train;
  private static int counter = 1;

  public FakeServiceClient() {
  }

  @Override
  public void reset(String trainId) {
    if (train != null) {
      train.reset();
    }
  }

  @Override
  public Train getTrain(String trainId) {
    return train;
  }

  @Override
  public Train makeReservation(Reservation reservation) {
    String bookingReference = reservation.bookingReference();
    for (var seat : reservation.seats()) {
      train.book(seat, bookingReference);
    }
    return train;
  }

  @Override
  public String getNewBookingReference() {
    counter++;
    return Integer.toString(counter);
  }

  public void setTrain(Train train) {
    this.train = train;
  }
}
