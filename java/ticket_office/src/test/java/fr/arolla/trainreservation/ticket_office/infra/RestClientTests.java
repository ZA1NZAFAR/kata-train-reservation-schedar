package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.Booking;
import fr.arolla.trainreservation.ticket_office.domain.SeatID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RestClientTests {
  private final String trainID = "express_2000";

  @Test
  static void canGetSeveralUniqueBookingReferences() {
    RestClient restClient = new RestClient();
    String first = restClient.getNewBookingReference();
    String second = restClient.getNewBookingReference();
    assertNotEquals(first, second);
  }

  @BeforeEach
  void resetTrain() {
    var client = new RestClient();
    client.resetTrain(trainID);
  }

  @Test
  void canGetTrain() {
    var client = new RestClient();
    var train = client.getTrain(trainID);
    assertEquals(16, train.seats().count());
  }

  @Test
  void canApplyBooking() {
    var client = new RestClient();
    client.resetTrain(trainID);
    var seats = List.of(new String[]{"1A", "2A"}).stream().map(s -> SeatID.parse(s));
    var booking = new Booking("abc123def", "express_2000", seats.toList());
    client.applyBooking(booking);
  }
}