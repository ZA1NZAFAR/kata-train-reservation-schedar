package fr.arolla.trainreservation.ticket_office.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.arolla.trainreservation.ticket_office.domain.Train;

import java.util.HashMap;

public class TrainSerializer {
  public String serialize(Train train) {
    HashMap<String, HashMap<String, String>> seats = new HashMap<>();
    train.seats().forEach(seat -> {
      HashMap<String, String> seatMap = new HashMap<>();
      seatMap.put("coach", seat.coach().toString());
      seatMap.put("seat_number", seat.number().toString());
      if (seat.isFree()) {
        seatMap.put("booking_reference", "");
      } else {
        seatMap.put("booking_reference", seat.bookingReference());
      }
      seats.put(seat.id().toString(), seatMap);
    });

    HashMap<String, HashMap<String, HashMap<String, String>>> res = new HashMap<>();
    res.put("seats", seats);
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    try {
      return ow.writeValueAsString(res);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Could not serialize train data: " + e);
    }
  }
}
