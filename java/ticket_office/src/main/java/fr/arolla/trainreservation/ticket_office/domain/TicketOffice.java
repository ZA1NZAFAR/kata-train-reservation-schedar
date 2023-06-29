package fr.arolla.trainreservation.ticket_office.domain;

public class TicketOffice {
  private final TrainRepository serviceClient;

  public TicketOffice(TrainRepository serviceClient) {
    this.serviceClient = serviceClient;
  }

  public Booking processRequest(BookingRequest request) {
    String trainID = request.trainID();
    int seatCount = request.seatCount();

    var bookingReference = serviceClient.getNewBookingReference();

    var train = serviceClient.getTrain(trainID);

    var seatFinder = new SeatFinder(train);
    var freeSeats = seatFinder.findSeats(seatCount);

    var booking = new Booking(bookingReference, trainID, freeSeats);
    serviceClient.applyBooking(booking);
    return booking;
  }
}
