package fr.arrolla.trainreservation.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arrolla.trainreservation.domain.CoachID;
import fr.arrolla.trainreservation.domain.Seat;
import fr.arrolla.trainreservation.domain.SeatNumber;
import fr.arrolla.trainreservation.domain.Train;

import java.util.ArrayList;

public class TrainParser {
  public Train parse(String trainDataJson) {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Seat> seats = new ArrayList<>();
    try {
      var tree = objectMapper.readTree(trainDataJson);
      var seatsNode = tree.get("seats");
      for (JsonNode node : seatsNode) {
        String bookingReference = null;
        var bookingReferenceNode = node.get("booking_reference");
        if (bookingReferenceNode != null && !bookingReferenceNode.isNull() && bookingReferenceNode.asText() != "") {
          bookingReference = bookingReferenceNode.asText();
        }
        String coach = node.get("coach").asText();
        String seat_number = node.get("seat_number").asText();
        Seat seat = new Seat(new SeatNumber(seat_number), new CoachID(coach), bookingReference);
        seats.add(seat);
      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    return new Train(seats);
  }
}
