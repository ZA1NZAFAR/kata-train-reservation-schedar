package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.Reservation;
import fr.arolla.trainreservation.ticket_office.domain.ServiceClient;
import fr.arolla.trainreservation.ticket_office.domain.Train;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServiceClient implements ServiceClient {
  private final RestTemplate restTemplate;
  private final String baseUrl = "http://127.0.0.1:8081";

  public HttpServiceClient() {
    restTemplate = new RestTemplate();
  }

  @Override
  public void reset(String trainId) {
    restTemplate.postForObject(baseUrl + "/reset/" + trainId, null, String.class);
  }

  @Override
  public Train getTrain(String trainId) {
    var json = restTemplate.getForObject(baseUrl + "/data_for_train/" + trainId, String.class);
    var parser = new TrainParser();
    return parser.parse(trainId, json);
  }

  @Override
  public Booking makeReservation(Reservation reservation) {
    String trainID = reservation.trainID();
    String bookingReference = reservation.bookingReference();
    List<String> seatIDs = reservation.seatIDs().stream().map(s -> s.toString()).toList();
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", reservation.trainID());
    payload.put("seats", seatIDs);
    payload.put("booking_reference", bookingReference);

    restTemplate.postForObject(baseUrl + "/reserve", payload, String.class);

    return new Booking(trainID, bookingReference, seatIDs);
  }

  @Override
  public String getNewBookingReference() {
    var restTemplate = new RestTemplate();
    String bookingReference = restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
    return bookingReference;
  }
}
