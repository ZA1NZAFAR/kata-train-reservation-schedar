package fr.arrolla.trainreservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BookingReferenceClientTest {
  @Test
  void canGetSeveralUniqueBookingReferences() {
    BookingReferenceClient client = new BookingReferenceClient();
    String first = client.getNewBookingReference();
    String second = client.getNewBookingReference();
    assertNotEquals(first, second);
  }
}
