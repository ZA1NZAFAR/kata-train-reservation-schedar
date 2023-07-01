package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.SeatId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RestClientTests {
  private final String trainId = "express_2000";
  private RestClient client;

  @BeforeEach
  void resetTrain() {
    client = new RestClient();
    client.resetTrain(trainId);
  }

  @Test
  void canGetSeveralUniqueBookingReferences() {
    String first = client.getNewBookingReference();
    String second = client.getNewBookingReference();
    
    assertNotEquals(first, second);
  }

  @Test
  void canGetTrain() {
    var train = client.getTrain(trainId);

    assertEquals(16, train.seats().count());
  }

  @Test
  void canApplyBooking() {
    var seats = List.of(new String[]{"1A", "2A"}).stream().map(s -> SeatId.parse(s));
    var booking = new Booking("abc123def", "express_2000", seats.toList());

    client.applyBooking(booking);
  }
}