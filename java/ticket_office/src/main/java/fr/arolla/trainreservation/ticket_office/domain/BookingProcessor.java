package fr.arolla.trainreservation.ticket_office.domain;

public class BookingProcessor {
  private final TrainRepository repository;
  private final BookingReferenceSource bookingReferenceSource;

  public BookingProcessor(BookingReferenceSource bookingReferenceSource, TrainRepository repository) {
    this.bookingReferenceSource = bookingReferenceSource;
    this.repository = repository;
  }

  public Booking processRequest(BookingRequest request) {
    var bookingReference = bookingReferenceSource.getNewBookingReference();

    String trainID = request.trainID();
    var train = repository.getTrain(trainID);

    int seatCount = request.seatCount();
    var seatFinder = new SeatFinder(train);
    var freeSeats = seatFinder.findSeats(seatCount);

    var booking = new Booking(bookingReference, trainID, freeSeats);
    repository.applyBooking(booking);

    return booking;
  }
}
