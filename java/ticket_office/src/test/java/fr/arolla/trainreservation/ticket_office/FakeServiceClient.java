package fr.arolla.trainreservation.ticket_office;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.ServiceClient;
import fr.arolla.trainreservation.ticket_office.domain.Train;

public class FakeServiceClient implements ServiceClient {
  private static int counter = 1;
  private Train train;

  public FakeServiceClient() {
  }

  @Override
  public void resetTrain(String trainID) {
    if (train != null) {
      train.reset();
    }
  }

  @Override
  public Train getTrain(String trainID) {
    return train;
  }

  @Override
  public void applyBooking(Booking booking) {
    String bookingReference = booking.reference();
    var seatIDs = booking.seatIDs();
    for (var seatID : seatIDs) {
      train.book(seatID, bookingReference);
    }
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
