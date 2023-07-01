package fr.arolla.trainreservation.ticket_office.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatTests {
  private Seat seat;

  @BeforeEach
  void setSeat() {
    var coachId = new CoachId("A");
    var seatNumber = new SeatNumber("1");
    var seatId = new SeatId(seatNumber, coachId);
    seat = Seat.free(seatId);
  }

  @Test
  void canParseSeatId() {
    String id = "1A";
    var parsed = SeatId.parse(id);
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
