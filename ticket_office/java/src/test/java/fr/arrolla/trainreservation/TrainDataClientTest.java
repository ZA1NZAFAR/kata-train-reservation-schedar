package fr.arrolla.trainreservation;

import org.junit.jupiter.api.Test;

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
}
