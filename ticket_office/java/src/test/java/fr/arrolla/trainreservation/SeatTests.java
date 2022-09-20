package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.AlreadyBookedException;
import fr.arrolla.trainreservation.domain.CoachID;
import fr.arrolla.trainreservation.domain.Seat;
import fr.arrolla.trainreservation.domain.SeatNumber;
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
