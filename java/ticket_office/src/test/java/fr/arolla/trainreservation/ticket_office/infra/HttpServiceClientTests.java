package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.Reservation;
import fr.arolla.trainreservation.ticket_office.domain.SeatID;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HttpServiceClientTests {
  @Test
  static void canGetSeveralUniqueBookingReferences() {
    HttpServiceClient client = new HttpServiceClient();
    String first = client.getNewBookingReference();
    String second = client.getNewBookingReference();
    assertNotEquals(first, second);
  }

  @Test
  void canResetExistingTrain() {
    var client = new HttpServiceClient();
    client.reset("express_2000");
  }

  @Test
  void canGetTrain() {
    var client = new HttpServiceClient();
    var train = client.getTrain("express_2000");
    assertEquals(16, train.seats().count());
  }

  @Test
  void canMakeReservation() {
    var client = new HttpServiceClient();
    var seats = List.of(new String[]{"1A", "2A"}).stream().map(s -> SeatID.parse(s));
    var reservation = new Reservation("express_2000", "abc123def", seats.toList());
    client.makeReservation(reservation);
  }
}