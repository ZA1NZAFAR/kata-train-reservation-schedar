package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.Reservation;
import fr.arrolla.trainreservation.domain.SeatID;
import fr.arrolla.trainreservation.domain.ServiceClient;
import fr.arrolla.trainreservation.domain.Train;

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
    String bookingReference = reservation.booking_reference();
    for (var seat : reservation.seats()) {
      var seatID = SeatID.parse(seat);
      train.book(seatID, bookingReference);
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
