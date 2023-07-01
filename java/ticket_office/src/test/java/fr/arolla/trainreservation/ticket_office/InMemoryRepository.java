package fr.arolla.trainreservation.ticket_office;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.Train;
import fr.arolla.trainreservation.ticket_office.domain.TrainRepository;

public class InMemoryRepository implements TrainRepository {
  private Train train;

  public InMemoryRepository() {
  }

  @Override
  public void resetTrain(String trainId) {
    if (train != null) {
      train.reset();
    }
  }

  @Override
  public Train getTrain(String trainId) {
    return train;
  }

  @Override
  public void applyBooking(Booking booking) {
    String bookingReference = booking.reference();
    var seatIds = booking.seatIds();
    for (var seatId : seatIds) {
      train.book(seatId, bookingReference);
    }
  }


  public void setTrain(Train train) {
    this.train = train;
  }
}
