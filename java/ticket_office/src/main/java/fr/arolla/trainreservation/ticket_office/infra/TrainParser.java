package fr.arolla.trainreservation.ticket_office.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arolla.trainreservation.ticket_office.domain.*;

import java.util.ArrayList;

public class TrainParser {
  public Train parse(String id, String json) {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Seat> seats = new ArrayList<>();
    try {
      var tree = objectMapper.readTree(json);
      var seatsNode = tree.get("seats");
      for (JsonNode node : seatsNode) {
        String coach = node.get("coach").asText();
        String seatNumber = node.get("seat_number").asText();
        SeatId seatId = new SeatId(new SeatNumber(seatNumber), new CoachId(coach));
        String bookingReference = null;
        var bookingReferenceNode = node.get("booking_reference");
        if (bookingReferenceNode != null && !bookingReferenceNode.asText().equals("")) {
          bookingReference = bookingReferenceNode.asText();
        }
        if(bookingReference == null) {
          seats.add(Seat.free(seatId));
        } else {
          seats.add(Seat.booked(seatId, bookingReference));
        }
      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    return new Train(id, seats);
  }
}
