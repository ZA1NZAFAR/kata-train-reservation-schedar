package fr.arolla.trainreservation.ticket_office.services;

import fr.arolla.trainreservation.ticket_office.entities.Seat;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@Service
public class BookingService {

  private final RestTemplate restTemplate;

  public BookingService() {
    restTemplate = new RestTemplate();
  }

  public String getBookingReference() {
    return restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
  }

  public String getTrainData(String trainId) {
    return restTemplate.getForObject("http://127.0.0.1:8081/data_for_train/" + trainId, String.class);
  }

  public Stream<Seat> getAvailableSeats(ArrayList<Seat> seats){
    return seats.stream().filter(seat -> seat.coach().equals("A") && seat.bookingReference() == null);
  }

  public List<String> getIdsToReserve(Stream<Seat> availableSeats, int seatCount) {
    return availableSeats.limit(seatCount).map(seat -> seat.number() + seat.coach()).toList();
  }

  public void saveNewSeats(String trainId, List<String> ids, String bookingReference) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", trainId);
    payload.put("seats", ids);
    payload.put("booking_reference", bookingReference);
    restTemplate.postForObject("http://127.0.0.1:8081/reserve", payload, String.class);
  }
}
