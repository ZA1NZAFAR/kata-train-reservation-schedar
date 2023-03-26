package fr.arolla.trainreservation;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestClient implements ServiceClient {
  RestTemplate restTemplate;

  public RestClient() {
    this.restTemplate = new RestTemplate();
  }

  @Override
  public String getBookingReference() {
    return restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
  }

  @Override
  public String getTrainData(String trainId) {
    return restTemplate.getForObject("http://127.0.0.1:8081/data_for_train/" + trainId, String.class);
  }

  @Override
  public void makeReservation(Map<String, Object> payload) {
    restTemplate.postForObject("http://127.0.0.1:8081/reserve", payload, String.class);
  }
}
