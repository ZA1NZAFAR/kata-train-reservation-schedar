package fr.arrolla.trainreservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    assertEquals(2, trainData.seats().stream().count());
  }

}
