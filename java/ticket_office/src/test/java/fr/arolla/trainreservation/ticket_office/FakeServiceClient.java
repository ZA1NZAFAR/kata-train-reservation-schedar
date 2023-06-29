package fr.arolla.trainreservation.ticket_office;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.Reservation;
import fr.arolla.trainreservation.ticket_office.domain.ServiceClient;
import fr.arolla.trainreservation.ticket_office.domain.Train;

public class FakeServiceClient implements ServiceClient {
  private static int counter = 1;
  private Train train;

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
  public Booking makeReservation(Reservation reservation) {
    String bookingReference = reservation.bookingReference();
    var seatIDs = reservation.seatIDs();
    for (var seatID : seatIDs) {
      train.book(seatID, bookingReference);
    }
    var seats = seatIDs.stream().map(s -> s.toString());
    return new Booking(reservation.trainID(), bookingReference, seats.toList());
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
