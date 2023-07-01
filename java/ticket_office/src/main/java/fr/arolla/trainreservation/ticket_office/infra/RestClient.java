package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.BookingReferenceSource;
import fr.arolla.trainreservation.ticket_office.domain.Train;
import fr.arolla.trainreservation.ticket_office.domain.TrainRepository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClient implements BookingReferenceSource, TrainRepository {
  private final RestTemplate restTemplate;
  private final String baseUrl = "http://127.0.0.1:8081";

  public RestClient() {
    restTemplate = new RestTemplate();
  }

  @Override
  public void resetTrain(String trainId) {
    restTemplate.postForObject(baseUrl + "/reset/" + trainId, null, String.class);
  }

  @Override
  public Train getTrain(String trainId) {
    var json = restTemplate.getForObject(baseUrl + "/data_for_train/" + trainId, String.class);
    var parser = new TrainParser();
    return parser.parse(trainId, json);
  }

  @Override
  public void applyBooking(Booking booking) {
    List<String> seatIds = booking.seatIds().stream().map(s -> s.toString()).toList();
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", booking.trainId());
    payload.put("seats", seatIds);
    payload.put("booking_reference", booking.reference());

    // Will throw if there are booking conflicts which should not happen if SeatFinder works
    // correctly
    // TODO: catch the 400 error here and re-throw a 500 ?
    restTemplate.postForObject(baseUrl + "/reserve", payload, String.class);
  }

  @Override
  public String getNewBookingReference() {
    return restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
  }
}
