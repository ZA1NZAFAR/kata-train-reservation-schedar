package fr.arrolla.trainreservation.domain;

import java.util.ArrayList;

public class TicketOffice {
  private final ServiceClient serviceClient;

  public TicketOffice(ServiceClient serviceClient) {
    this.serviceClient = serviceClient;
  }

  public Train reserve(String trainId, int seatCount) {
    // Fetch booking reference and train data using the serviceClient
    var bookingReference = serviceClient.getNewBookingReference();
    var train = serviceClient.getTrain(trainId);

    // Find available seats
    var inFirstCoach = train.seatsInCoach(new CoachID("A"));
    var availableSeats = inFirstCoach.filter(Seat::isFree).sorted().toList();

    var seats = new ArrayList<String>();
    for (int i = 0; i < seatCount; i++) {
      var availableSeat = availableSeats.get(i);
      seats.add(availableSeat.id().toString());
    }

    // Make the reservation
    var reservation = new Reservation(trainId, bookingReference, seats);
    var updatedTrain = serviceClient.makeReservation(reservation);

    return updatedTrain;
  }
}
