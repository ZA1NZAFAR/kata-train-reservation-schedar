package fr.arrolla.trainreservation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class TrainDataParser {
  public TrainData parse(String trainDataJson) {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Seat> seats = new ArrayList<>();
    try {
      var tree = objectMapper.readTree(trainDataJson);
      var seatsNode = tree.get("seats");
      for (JsonNode node : seatsNode) {
        String bookingReference = node.get("booking_reference").asText();
        String coach = node.get("coach").asText();
        String seat_number = node.get("seat_number").asText();
        Seat seat = new Seat(seat_number, coach, bookingReference);
        seats.add(seat);
      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    return new TrainData(seats);
  }
}
