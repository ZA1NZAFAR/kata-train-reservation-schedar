package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.Reservation;
import fr.arrolla.trainreservation.domain.ServiceClient;
import fr.arrolla.trainreservation.domain.TrainData;

public class FakeServiceClient implements ServiceClient {
  private TrainData trainData;
  private static int counter = 1;

  public FakeServiceClient() {
  }

  @Override
  public void reset(String trainId) {
    if (trainData != null) {
      trainData.seats().clear();
    }
  }

  @Override
  public TrainData getTrainData(String trainId) {
    return trainData;
  }

  @Override
  public TrainData makeReservation(Reservation reservation) {
    String bookingReference = reservation.booking_reference();
    for (var seat : reservation.seats()) {
      var matchingSeat = trainData.seats().stream().filter(s -> s.id().toString().equals(seat)).findFirst().get();
      matchingSeat.setBookingReference(bookingReference);
    }
    return trainData;
  }

  @Override
  public String getNewBookingReference() {
    counter++;
    return Integer.toString(counter);
  }

  public void setTrainData(TrainData trainData) {
    this.trainData = trainData;
  }
}
