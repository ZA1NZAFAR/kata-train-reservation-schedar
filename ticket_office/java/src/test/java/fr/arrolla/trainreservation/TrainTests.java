package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.CoachID;
import fr.arrolla.trainreservation.domain.NoSuchSeatException;
import fr.arrolla.trainreservation.domain.SeatID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrainTests {

  @Test
  void getSeatsInCoach() {
    var train = Helpers.makeEmptyTrain();

    var actual = train.seatsInCoach(new CoachID("A")).sorted().toList();

    assertEquals(9, actual.size());
  }

  @Test
  void bookAnExistingSeat() {
    var train = Helpers.makeEmptyTrain();

    train.book(SeatID.parse("1A"), "123");
  }

  @Test
  void tryingToBookANonExistingSeat() {
    var train = Helpers.makeEmptyTrain();

    assertThrows(NoSuchSeatException.class, () -> {
      train.book(SeatID.parse("5G"), "123");
    });
  }
}
