package fr.arolla.trainreservation.train_data;

public class NoSuchTrain extends RuntimeException {
  public NoSuchTrain(String trainID) {
    super(String.format("No train found for id %s", trainID));
  }
}
