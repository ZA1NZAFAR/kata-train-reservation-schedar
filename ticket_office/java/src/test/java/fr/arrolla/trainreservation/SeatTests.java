package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatTests {
  private Seat seat;

  @BeforeEach
  void setSeat() {
    var coachId = new CoachID("A");
    var seatNumber = new SeatNumber("1");
    seat = new Seat(seatNumber, coachId);
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

    assertFalse(seat.isFree());
    assertEquals("123", seat.bookingReference());
  }

  @Test
  void canBookTwiceWithTheSameBookingReference() {
    seat.book("123");
    seat.book("123");

    assertFalse(seat.isFree());
  }

  @Test
  void cannotBookWithDifferentBookingReferences() {
    seat.book("123");
    assertThrows(AlreadyBookedException.class, () -> {
      seat.book("345");
    });

  }
}
