package fr.arrolla.trainreservation.infra;

import org.springframework.web.client.RestTemplate;

public class BookingReferenceClient {
  public String getNewBookingReference() {
    var restTemplate = new RestTemplate();
    String bookingReference = restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
    return bookingReference;
  }
}
