package fr.arolla.trainreservation.infra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainParserTests {
  @Test
  void parseTwoSeats() {
    String json = """    
      {
        "seats": {
          "1A": {
            "booking_reference": "",
              "coach": "A",
              "seat_number": "1"
          },
          "1B": {
            "booking_reference": "abcdef123",
              "coach": "B",
              "seat_number": "1"
          }
        }
      }
      """;

    var parser = new TrainParser();

    var train = parser.parse("express_2000", json);

    var seats = train.seats().sorted().toList();
    assertEquals(2, seats.size());
    var seat1 = seats.get(0);
    var seat2 = seats.get(1);
    assertTrue(seat1.isFree());
    assertEquals("1", seat1.number().toString());
    assertEquals("A", seat1.coach().toString());
    assertTrue(seat2.isBooked());
    assertEquals("1", seat2.number().toString());
    assertEquals("B", seat2.coach().toString());
    assertEquals("abcdef123", seat2.bookingReference());
  }

}
