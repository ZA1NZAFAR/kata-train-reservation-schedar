package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.Reservation;
import fr.arrolla.trainreservation.infra.HttpServiceClient;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HttpServiceClientTests {
  @Test
  void canResetExistingTrain() {
    var client = new HttpServiceClient();
    client.reset("express_2000");
  }

  @Test
  void canGetTrainData() {
    var client = new HttpServiceClient();
    client.getTrain("express_2000");
  }

  @Test
  void canMakeReservation() {
    var client = new HttpServiceClient();
    var seats = List.of(new String[]{"1A", "2A"});
    var reservation = new Reservation("express_2000", "abc123def", seats);
    client.makeReservation(reservation);
  }
}
