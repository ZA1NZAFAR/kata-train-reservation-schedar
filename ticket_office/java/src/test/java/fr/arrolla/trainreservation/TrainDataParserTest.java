package fr.arrolla.trainreservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TrainDataParserTest {
  @Test
  void parseTwoSeats() {
    String trainDataJson = """    
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

    var parser = new TrainDataParser();
    var trainData = parser.parse(trainDataJson);

    var seats = trainData.seats();

    assertEquals(2, seats.size());
    var seat1 = seats.get(0);
    var seat2 = seats.get(1);
    assertEquals("A", seat1.coach().toString());
    assertEquals("1", seat1.number().toString());
    assertNull(seat1.bookingReference());
    assertEquals("B", seat2.coach().toString());
    assertEquals("1", seat2.number().toString());
    assertEquals("abcdef123", seat2.bookingReference());
  }

}
