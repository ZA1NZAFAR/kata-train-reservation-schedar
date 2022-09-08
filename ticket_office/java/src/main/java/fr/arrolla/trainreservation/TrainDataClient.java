package fr.arrolla.trainreservation;

import org.springframework.web.client.RestTemplate;

public class TrainDataClient {
  private final RestTemplate restTemplate;
  private final String baseUrl = "http://127.0.0.1:8081";

  public TrainDataClient() {
    restTemplate = new RestTemplate();
  }

  public void reset(String trainId) {
    restTemplate.postForObject(baseUrl + "/reset/" + trainId, null, String.class);
  }

  public void getTrainData(String trainId) {
    String res = restTemplate.getForObject(baseUrl + "/data_for_train/" + trainId, String.class);
    System.out.println(res);
  }
}
