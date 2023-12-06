package fr.arolla.trainreservation.ticket_office.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arolla.trainreservation.ticket_office.DTO.Seat;
import fr.arolla.trainreservation.ticket_office.DTO.BookingRequest;
import fr.arolla.trainreservation.ticket_office.DTO.BookingResponse;
import fr.arolla.trainreservation.ticket_office.domain.BookingDomain;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@RestController
public class BookingInfrastructure {
  private final BookingDomain bookingDomain;
  private final RestTemplate restTemplate;

  BookingInfrastructure() {
    bookingDomain = new BookingDomain();
    restTemplate = new RestTemplate();
  }

  @RequestMapping("/reserve")
  BookingResponse reserve(@RequestBody BookingRequest bookingRequest) {
    var seatCount = bookingRequest.count();
    var trainId = bookingRequest.train_id();

    // Step 1: Get a booking reference
    var bookingReference = restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);

    // Step 2: Retrieve train data for the given train ID
    var json = restTemplate.getForObject("http://127.0.0.1:8081/data_for_train/" + trainId, String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    Stream<Seat> availableSeats;
    try {
      var tree = objectMapper.readTree(json);
      ArrayList<Seat> seats = bookingDomain.extractSeats(tree);

      // Step 3: find available seats
      availableSeats = bookingDomain.getAvailableSeats(seats, seatCount);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // Step 4: call the '/reserve' end point
    var ids = bookingDomain.getIdsToReserve(availableSeats, seatCount);
    saveNewSeats(trainId, ids, bookingReference);

    // Step 5: return reference and booked seats
    return new BookingResponse(trainId, bookingReference, ids);
  }

  private void saveNewSeats(String trainId, List<String> ids, String bookingReference) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", trainId);
    payload.put("seats", ids);
    payload.put("booking_reference", bookingReference);
    restTemplate.postForObject("http://127.0.0.1:8081/reserve", payload, String.class);
  }
}
