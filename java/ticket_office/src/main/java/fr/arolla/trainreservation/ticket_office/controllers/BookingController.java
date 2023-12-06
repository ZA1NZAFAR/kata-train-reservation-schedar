package fr.arolla.trainreservation.ticket_office.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arolla.trainreservation.ticket_office.entities.Seat;
import fr.arolla.trainreservation.ticket_office.entities.BookingRequest;
import fr.arolla.trainreservation.ticket_office.entities.BookingResponse;
import fr.arolla.trainreservation.ticket_office.services.BookingService;
import fr.arolla.trainreservation.ticket_office.utils.SeatUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Stream;


@RestController
public class BookingController {
  BookingService bookingService;

  BookingController() {
    bookingService = new BookingService();
  }

  @RequestMapping("/reserve")
  BookingResponse reserve(@RequestBody BookingRequest bookingRequest) {
    var seatCount = bookingRequest.count();
    var trainId = bookingRequest.train_id();

    // Step 1: Get a booking reference
    var bookingReference = bookingService.getBookingReference();

    // Step 2: Retrieve train data for the given train ID
    var json = bookingService.getTrainData(trainId);

    ObjectMapper objectMapper = new ObjectMapper();
    Stream<Seat> availableSeats;
    try {
      var tree = objectMapper.readTree(json);
      ArrayList<Seat> seats = SeatUtils.extractSeats(tree);
        // Step 3: find available seats
      availableSeats = bookingService.getAvailableSeats(seats);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // Step 4: call the '/reserve' end point
    var ids = bookingService.getIdsToReserve(availableSeats, seatCount);
    bookingService.saveNewSeats(trainId, ids, bookingReference);

    // Step 5: return reference and booked seats
    return new BookingResponse(trainId, bookingReference, ids);
  }
}
