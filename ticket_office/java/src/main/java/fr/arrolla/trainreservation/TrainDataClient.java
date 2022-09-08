package fr.arrolla.trainreservation;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

public class TrainDataClient {
  public void reset() {
    var restTemplate = new RestTemplate();
    final String trainId = "express_2000";
    var body = new HashMap<String, String>();
    body.put("train_id", trainId);
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.MULTIPART_FORM_DATA));
    HttpEntity<?> entity = new HttpEntity(body, headers);
    String res = restTemplate.postForObject("http://127.0.0.1:5000/post", body, String.class);
    System.out.println(res);
  }
}
