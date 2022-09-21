package fr.arrolla.trainreservation.domain;

import fr.arrolla.trainreservation.Helpers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrainTests {
  @Test
  void getCoaches() {
    var train = Helpers.makeEmptyTrain();
    var returnedLetters = train.getCoaches().stream().map(c -> c.toString()).toList();
    assertEquals(List.of("A", "B", "C", "D", "E"), returnedLetters);
  }

  @Test
  void getSeatsInCoach() {
    var train = Helpers.makeEmptyTrain();

    var actual = train.seatsInCoach(new CoachID("A")).sorted().toList();

    assertEquals(10, actual.size());
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

  @Test
  void computeOccupancyPerCoach() {
    var train = Helpers.makeEmptyTrain();
    var coach = new CoachID("A");
    var bookingReference = "123";
    var numbers = IntStream.range(0, 6);
    numbers.forEach(i -> {
      var number = new SeatNumber(Integer.toString(i));
      var seat = new SeatID(number, coach);
      train.book(seat, bookingReference);
    });

    assertEquals(0.6, train.occupancyForCoach(coach), 0.001);
  }
}
