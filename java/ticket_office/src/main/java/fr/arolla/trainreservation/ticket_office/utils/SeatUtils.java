package fr.arolla.trainreservation.ticket_office.utils;

import com.fasterxml.jackson.databind.JsonNode;
import fr.arolla.trainreservation.ticket_office.entities.Seat;

import java.util.ArrayList;

public class SeatUtils {

  public static ArrayList<Seat> extractSeats(JsonNode tree) {
    ArrayList<Seat> seats = new ArrayList<>();
    var seatsNode = tree.get("seats");

    for (JsonNode node : seatsNode) {
      String coach = node.get("coach").asText();
      String seatNumber = node.get("seat_number").asText();
      var jsonBookingReference = node.get("booking_reference").asText();
      Seat seat;
      if (jsonBookingReference.isEmpty()) {
        seat = new Seat(seatNumber, coach, null);
      } else {
        seat = new Seat(seatNumber, coach, jsonBookingReference);
      }
      seats.add(seat);
    }
    return seats;
  }
}
