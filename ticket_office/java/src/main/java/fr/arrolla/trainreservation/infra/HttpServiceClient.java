package fr.arrolla.trainreservation.infra;

import fr.arrolla.trainreservation.domain.Reservation;
import fr.arrolla.trainreservation.domain.ServiceClient;
import fr.arrolla.trainreservation.domain.TrainData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
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
  public TrainData getTrainData(String trainId) {
    var json = restTemplate.getForObject(baseUrl + "/data_for_train/" + trainId, String.class);
    var parser = new TrainDataParser();
    return parser.parse(json);
  }

  @Override
  public TrainData makeReservation(Reservation reservation) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", reservation.trainId());
    payload.put("seats", reservation.seats());
    payload.put("booking_reference", reservation.booking_reference());
    String json = restTemplate.postForObject(baseUrl + "/reserve", payload, String.class);
    var parser = new TrainDataParser();
    return parser.parse(json);
  }

  @Override
  public String getNewBookingReference() {
    var restTemplate = new RestTemplate();
    String bookingReference = restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
    return bookingReference;
  }
}
