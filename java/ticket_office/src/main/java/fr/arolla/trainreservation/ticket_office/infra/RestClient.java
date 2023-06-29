package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.Train;
import fr.arolla.trainreservation.ticket_office.domain.TrainRepository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClient implements TrainRepository {
  private final RestTemplate restTemplate;
  private final String baseUrl = "http://127.0.0.1:8081";

  public RestClient() {
    restTemplate = new RestTemplate();
  }

  @Override
  public void resetTrain(String trainID) {
    restTemplate.postForObject(baseUrl + "/reset/" + trainID, null, String.class);
  }

  @Override
  public Train getTrain(String trainID) {
    var json = restTemplate.getForObject(baseUrl + "/data_for_train/" + trainID, String.class);
    var parser = new TrainParser();
    return parser.parse(trainID, json);
  }

  @Override
  public void applyBooking(Booking booking) {
    String trainID = booking.trainID();
    String bookingReference = booking.reference();
    List<String> seatIDs = booking.seatIDs().stream().map(s -> s.toString()).toList();
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", booking.trainID());
    payload.put("seats", seatIDs);
    payload.put("booking_reference", bookingReference);

    // Will throw if there are booking conflicts which should not happen if SeatFinder works
    // correctly

    // TODO: catch the 400 error here and re-throw a 500 ?
    restTemplate.postForObject(baseUrl + "/reserve", payload, String.class);
  }

  @Override
  public String getNewBookingReference() {
    var restTemplate = new RestTemplate();
    String bookingReference = restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
    return bookingReference;
  }
}
