package fr.arrolla.trainreservation.infra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class BookingReferenceClientTests {
  @Test
  void canGetSeveralUniqueBookingReferences() {
    HttpServiceClient client = new HttpServiceClient();
    String first = client.getNewBookingReference();
    String second = client.getNewBookingReference();
    assertNotEquals(first, second);
  }
}