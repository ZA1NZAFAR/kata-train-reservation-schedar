package fr.arolla.trainreservation.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.arolla.trainreservation.domain.ServiceClient;
import fr.arolla.trainreservation.domain.Train;
import fr.arolla.trainreservation.infra.controllers.BookingRequest;
import fr.arolla.trainreservation.infra.controllers.BookingResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
  public Train getTrain(String trainId) {
    var json = restTemplate.getForObject("http://127.0.0.1:8081/data_for_train/" + trainId, String.class);
    return TrainDataParser.parseTrainData(json);
  }

  @Override
  public void makeReservation(Reservation reservation) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", reservation.trainId());
    payload.put("seats", reservation.seats());
    payload.put("booking_reference", reservation.bookingReference());
    restTemplate.postForObject("http://127.0.0.1:8081/reserve", payload, String.class);
  }

  public void reset(String trainId) {
    restTemplate.postForObject("http://127.0.0.1:8081" + "/reset/" + trainId, null, String.class);
  }

  public BookingResponse book(BookingRequest request) throws JsonProcessingException {
    var mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(request);
    var headers = new HttpHeaders();
    var jsonContentType = new MediaType(
      org.springframework.http.MediaType.APPLICATION_JSON.getType(),
      org.springframework.http.MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8
    );
    headers.setContentType(jsonContentType);
    var requestEntity = new HttpEntity<String>(requestJson, headers);
    var json = restTemplate.postForObject("http://127.0.0.1:8083" + "/reserve", requestEntity, String.class);

    var objectMapper = new ObjectMapper();
    var bookingResponse = objectMapper.readValue(json, BookingResponse.class);
    return bookingResponse;
  }


}
