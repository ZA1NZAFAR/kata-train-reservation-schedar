package fr.arrolla.trainreservation;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TrainDataClient {
  private final RestTemplate restTemplate;
  private final String baseUrl = "http://127.0.0.1:8081";

  public TrainDataClient() {
    restTemplate = new RestTemplate();
  }

  public void reset(String trainId) {
    restTemplate.postForObject(baseUrl + "/reset/" + trainId, null, String.class);
  }

  public TrainData getTrainData(String trainId) {
    var json = restTemplate.getForObject(baseUrl + "/data_for_train/" + trainId, String.class);
    var parser = new TrainDataParser();
    return parser.parse(json);
  }

  public void makeReservation(Reservation reservation) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", reservation.trainId());
    payload.put("seats", reservation.seats());
    payload.put("booking_reference", reservation.bookingReference());
    String res = restTemplate.postForObject(baseUrl + "/reserve", payload, String.class);
    System.out.println(res);
  }
}
