package fr.arrolla.trainreservation.domain;

import java.util.ArrayList;

public class TicketOffice {
  private final ServiceClient serviceClient;

  public TicketOffice(ServiceClient serviceClient) {
    this.serviceClient = serviceClient;
  }

  public Train reserve(String trainId, int seatCount) {
    var bookingReference = serviceClient.getNewBookingReference();
    var train = serviceClient.getTrain(trainId);

    var inFirstCoach = train.seatsInCoach(new CoachID("A"));
    var availableSeats = inFirstCoach.filter(Seat::isFree).sorted().toList();

    var seats = new ArrayList<String>();
    for (int i = 0; i < seatCount; i++) {
      var availableSeat = availableSeats.get(i);
      seats.add(availableSeat.id().toString());
    }

    var reservation = new Reservation(trainId, bookingReference, seats);

    var updatedTrain = serviceClient.makeReservation(reservation);
    return updatedTrain;
  }
}
