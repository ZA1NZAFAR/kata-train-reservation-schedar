package fr.arolla.trainreservation.domain;

public class TicketOffice {
  private final ServiceClient serviceClient;

  public TicketOffice(ServiceClient serviceClient) {
    this.serviceClient = serviceClient;
  }

  public Train reserve(String trainId, int seatCount) {
    var bookingReference = serviceClient.getNewBookingReference();
    var train = serviceClient.getTrain(trainId);

    var seatFinder = new SeatFinder(train);
    var freeSeats = seatFinder.findSeats(seatCount);

    var reservation = new Reservation(trainId, bookingReference, freeSeats.toList());
    var updatedTrain = serviceClient.makeReservation(reservation);

    return updatedTrain;
  }
}
