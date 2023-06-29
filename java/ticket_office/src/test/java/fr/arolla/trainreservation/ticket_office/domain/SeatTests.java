package fr.arolla.trainreservation.ticket_office.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatTests {
  private Seat seat;

  @BeforeEach
  void setSeat() {
    var coachID = new CoachID("A");
    var seatNumber = new SeatNumber("1");
    seat = new Seat(seatNumber, coachID);
  }

  @Test
  void canParseSeatID() {
    String id = "1A";
    var parsed = SeatID.parse(id);
    assertEquals("1A", parsed.toString());
  }

  @Test
  void canBookOnce() {
    seat.book("123");

    assertTrue(seat.isBooked());
    assertEquals("123", seat.bookingReference());
  }

  @Test
  void canBookTwiceWithTheSameBookingReference() {
    seat.book("123");
    seat.book("123");

    assertTrue(seat.isBooked());
  }

  @Test
  void cannotBookWithDifferentBookingReferences() {
    seat.book("123");
    assertThrows(AlreadyBookedException.class, () -> {
      seat.book("345");
    });

  }
}
