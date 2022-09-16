package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.Reservation;
import fr.arrolla.trainreservation.infra.TrainDataClient;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TrainDataClientTest {
  @Test
  void canResetExistingTrain() {
    var client = new TrainDataClient();
    client.reset("express_2000");
  }

  @Test
  void canGetTrainData() {
    var client = new TrainDataClient();
    client.getTrainData("express_2000");
  }

  @Test
  void canMakeReservation() {
    var client = new TrainDataClient();
    var seats = List.of(new String[]{"1A", "2A"});
    var reservation = new Reservation("express_2000", "abc123def", seats);
    client.makeReservation(reservation);
  }
}
