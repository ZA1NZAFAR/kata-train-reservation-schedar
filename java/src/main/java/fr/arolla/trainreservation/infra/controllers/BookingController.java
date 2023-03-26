package fr.arolla.trainreservation.infra.controllers;

import fr.arolla.trainreservation.domain.SeatFinder;
import fr.arolla.trainreservation.domain.ServiceClient;
import fr.arolla.trainreservation.infra.Reservation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookingController {
  private final ServiceClient client;

  BookingController(ServiceClient client) {
    this.client = client;
  }


  @RequestMapping("/reserve")
  BookingResponse reserve(@RequestBody BookingRequest bookingRequest) {
    var bookingReference = client.getBookingReference();

    var trainId = bookingRequest.train_id();
    var train = client.getTrain(trainId);

    var seatCount = bookingRequest.seat_count();
    var seats = train.getSeats();
    var seatIds = SeatFinder.findSeats(seats.stream(), seatCount).toList();

    var reservation = new Reservation(trainId, seatIds, bookingReference);
    client.makeReservation(reservation);

    return new BookingResponse(bookingReference, seatIds);
  }

}
