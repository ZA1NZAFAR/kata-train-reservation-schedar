package fr.arolla.trainreservation.infra.controllers;

import fr.arolla.trainreservation.domain.ServiceClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class BookingController {
  private final ServiceClient client;

  BookingController(ServiceClient client) {
    this.client = client;
  }


  @RequestMapping("/reserve")
  BookingResponse reserve(@RequestBody BookingRequest bookingRequest) {
    var seatCount = bookingRequest.seat_count();
    var trainId = bookingRequest.train_id();


    // Step 1: Get a booking refenrence
    var bookingReference = client.getBookingReference();

    // Step 2: Retrieve train data for the given train ID
    var train = client.getTrain(trainId);

    // Step 3: find available seats (hard-code coach 'A' for now)
    var seats = train.getSeats();
    var inFirstCoach = seats.stream().filter(seat -> seat.coach().equals("A"));
    var availableSeats = inFirstCoach.filter(seat -> seat.bookingReference() == null);

    // Step 4: call the '/reserve' end point
    var toReserve = availableSeats.limit(seatCount);
    var ids = toReserve.map(seat -> seat.number() + seat.coach()).toList();

    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", trainId);
    payload.put("seats", ids);
    payload.put("booking_reference", bookingReference);
    client.makeReservation(payload);

    // Step 5: return reference and booked seats
    return new BookingResponse(bookingReference, ids);
  }

}
