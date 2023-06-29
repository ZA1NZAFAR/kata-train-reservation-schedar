package fr.arolla.trainreservation.ticket_office.domain;

import fr.arolla.trainreservation.ticket_office.Helpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SeatFinderTests {
  private final String trainID = "express_2000";
  private Train train;
  private SeatFinder seatFinder;

  @BeforeEach
  void init() {
    train = Helpers.makeEmptyTrain();
  }

  @Test
  void bookingFourSeatsFromEmptyTrain() {
    setupBookedSeats(List.of());

    var found = seatFinder.findSeats(4);

    checkSeats(found, 4);
  }

  @Test
  void bookingFourAdditionalSeats() {
    setupBookedSeats(List.of("0A", "1A", "2A", "3A"));

    var found = seatFinder.findSeats(4);

    checkSeats(found, 4);
  }

  @Test
  void useSecondCoachWhenFirstCoachIsAlmostFull() {
    setupBookedSeats(List.of("0A", "1A", "2A", "3A", "4A", "5A", "6A"));

    var found = seatFinder.findSeats(2);

    checkSeats(found, 2);
  }

  @Test
  void throwsWhenNotEnoughFreeSeats() {
    train.getCoaches().forEach(coach -> {
      bookCoachAt70Percent(coach);
    });
    seatFinder = new SeatFinder(train);

    assertThrows(NotEnoughFreeSeatsException.class, () -> {
      seatFinder.findSeats(1);
    });

  }

  void setupBookedSeats(List<String> bookedSeats) {
    train = Helpers.trainWithBookedSeats(bookedSeats.stream());
    seatFinder = new SeatFinder(train);
  }

  void bookCoachAt70Percent(CoachID coach) {
    for (int i = 0; i <= 7; i++) {
      var number = new SeatNumber(String.valueOf(i));
      var seatID = new SeatID(number, coach);
      train.book(seatID, "old");
    }
  }

  void checkSeats(List<SeatID> seats, int expectedCount) {

    // Check we have the expected count
    assertEquals(expectedCount, seats.size());

    // Use Train.applyReservation so that we're sure a booking can happen
    train.applyBooking(new Booking("newReference", trainID, seats));

    // Check that all seats are in the same coach
    var coachIDs = seats.stream().map(s -> s.coach()).collect(Collectors.toSet());
    assertEquals(1, coachIDs.size(), String.format("Expected all seats to be in the same coach, %s", seats));

    // Check occupancy of each coach
    train.getCoaches().forEach(coachID -> {
      var occupancy = train.occupancyForCoach(coachID);
      if (occupancy > 0.7) {
        fail(String.format("Coach %s does not have enough free seats\n%s", coachID, train));
      }
    });
  }

}