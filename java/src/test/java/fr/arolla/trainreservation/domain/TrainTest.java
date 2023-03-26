package fr.arolla.trainreservation.domain;

import fr.arolla.trainreservation.Helpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TrainTest {
  private Train train;

  @BeforeEach
  void setUp() {
    train = Helpers.makeEmptyTrain();
  }

  @Test
  void booking_non_existing_seat() {
    assertThrows(NoSuchSeatException.class, () ->
      train.bookSeat("no-such", "123abc")
    );
  }

}