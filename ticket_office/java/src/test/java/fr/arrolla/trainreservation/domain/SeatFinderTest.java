package fr.arrolla.trainreservation.domain;

import fr.arrolla.trainreservation.Helpers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatFinderTest {
  private final String trainID = "express_2000";
  private Train train;
  private SeatFinder seatFinder;


  @Test
  void findSeatsWhenTrainIsEmpty() {
    setupBookedSeats(List.of());

    var found = seatFinder.findSeats(4);

    checkSeats(found, 4);
  }

  @Test
  void findFourMoreSeatsWhenFourSeatsAreAlreadyBooked() {
    setupBookedSeats(List.of("0A", "1A", "2A", "3A"));

    var found = seatFinder.findSeats(4);


    checkSeats(found, 4);
  }

  @Test
  void useSecondCoachWhenFirstCoachIsFull() {
    setupBookedSeats(List.of("0A", "1A", "2A", "3A", "4A", "5A", "6A", "7A", "8A", "9A"));

    var found = seatFinder.findSeats(4);

    checkSeats(found, 4);
  }

  void setupBookedSeats(List<String> bookedSeats) {
    train = Helpers.trainWithBookedSeats(bookedSeats.stream());
    seatFinder = new SeatFinder(train);
  }

  void checkSeats(Stream<SeatID> seatStream, int expectedCount) {
    var seats = seatStream.sorted().toList();

    // Check that all seats are in the same coach
    var coachIDs = seats.stream().map(s -> s.getCoach()).collect(Collectors.toSet());
    assertEquals(1, coachIDs.size(), "Expected all seats to be in the same coach");

    // Check we have the expected count
    assertEquals(expectedCount, seats.size());

    // Use Train.applyReservation so that we're sure a booking can happen
    train.applyReservation(new Reservation(trainID, "new", seats));
  }


}