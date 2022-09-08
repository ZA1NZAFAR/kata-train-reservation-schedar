package fr.arrolla.trainreservation;

import org.springframework.web.client.RestTemplate;

public class BookingReferenceClient {
  public String getNewBookingReference() {
    var restTemplate = new RestTemplate();
    String bookingReference = restTemplate.getForObject("http://127.0.0.1:8082/booking_reference", String.class);
    return bookingReference;

  }

    /*
        HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<Employee> entity = new HttpEntity<Employee>(product,headers);

    ResponseEntity<Employee> response = restTemplate.exchange(
            "http://hello-server/rest/employee", HttpMethod.POST, entity, Employee.class);

    return response.getBody();
     */

}
