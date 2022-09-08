package fr.arrolla.trainreservation;

import org.junit.jupiter.api.Test;

public class TrainDataClientTest {
  @Test
  void canReset() {
    var client = new TrainDataClient();
    client.reset();
  }
}
