package fr.arolla.trainreservation.ticket_office.domain;

import com.fasterxml.jackson.databind.JsonNode;
import fr.arolla.trainreservation.ticket_office.domain.DTO.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookingDomainService {

  public ArrayList<Seat> extractSeats(JsonNode tree) {
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

  public Stream<Seat> getAvailableSeats(ArrayList<Seat> seats, int numberSeatsToReserve) {
    if (isGlobalOccupancyOver70(seats, numberSeatsToReserve)) {
      throw new RuntimeException("Global occupancy over 70%");
    }
    return seats.stream().filter(seat -> seat.coach().equals("A") && seat.bookingReference() == null);
  }

  public List<String> getIdsToReserve(Stream<Seat> availableSeats, int seatCount) {
    return availableSeats.limit(seatCount).map(seat -> seat.number() + seat.coach()).toList();
  }

  public boolean isGlobalOccupancyOver70(List<Seat> seats, int numberSeatsToReserve) {
    var count = seats.stream().filter(seat -> seat.bookingReference() != null && !seat.bookingReference().isEmpty()).count();
    var occupancy = (count + numberSeatsToReserve) / (double) seats.size();
    return occupancy > 0.7;
  }
}
