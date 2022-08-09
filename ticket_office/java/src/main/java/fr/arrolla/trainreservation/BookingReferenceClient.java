package fr.arrolla.trainreservation;

import org.springframework.web.client.RestTemplate;

public class BookingReferenceClient {
    public String getNewBookingReference() {
        RestTemplate restTemplate = new RestTemplate();
        String bookingReference = restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
        return bookingReference;
    }
}
