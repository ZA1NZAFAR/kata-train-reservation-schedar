package fr.arolla.trainreservation.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SeatTest {

  @Test
  void can_book_free_seat() {
    var seat = new Seat("1", "A", "");
    seat.book("123abc");
    assertEquals(seat.bookingReference(), "123abc");
  }

  @Test
  void cannot_book_seat_twice_with_different_booking_references() {
    var seat = new Seat("1", "A", "123abc");
    assertThrows(RuntimeException.class, () ->
      seat.book("345def")
    );
  }
}