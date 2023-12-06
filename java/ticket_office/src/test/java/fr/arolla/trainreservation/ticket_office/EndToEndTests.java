package fr.arolla.trainreservation.ticket_office;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.arolla.trainreservation.ticket_office.DTO.BookingRequest;
import fr.arolla.trainreservation.ticket_office.DTO.BookingResponse;
import fr.arolla.trainreservation.ticket_office.DTO.Seat;
import fr.arolla.trainreservation.ticket_office.domain.BookingDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EndToEndTests {
  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
    MediaType.APPLICATION_JSON.getType(),
    MediaType.APPLICATION_JSON.getSubtype(),
    StandardCharsets.UTF_8
  );

  @Autowired
  private MockMvc mockMvc;

  @Test
  void reserve_two_seats_from_empty_train() throws Exception {
    final String trainId = "express_2000";
    var restTemplate = new RestTemplate();
    restTemplate.postForObject("http://127.0.0.1:8081" + "/reset/" + trainId, null, String.class);

    var request = new BookingRequest(trainId, 2);
    var mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(request);
    String url = "http://127.0.0.1:8083/reserve";

    var result = mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
        .content(requestJson))
      .andExpect(status().isOk())
      .andReturn()
      .getResponse();

    var json = result.getContentAsString();
    var objectMapper = new ObjectMapper();
    var bookingResponse = objectMapper.readValue(json, BookingResponse.class);

    var expected = List.of("1A", "2A");
    assertEquals(expected, bookingResponse.seats());
  }

  @Test
  void reserve_two_additional_seats() throws Exception {
    // Reset
    final String trainId = "express_2000";
    var restTemplate = new RestTemplate();
    restTemplate.postForObject("http://127.0.0.1:8081" + "/reset/" + trainId, null, String.class);

    // Book 2 seats
    var request = new BookingRequest(trainId, 2);
    var mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(request);
    String url = "http://127.0.0.1:8083/reserve";

    mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
        .content(requestJson))
      .andExpect(status().isOk());

    // Book 2 additional seats
    var result = mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
        .content(requestJson))
      .andExpect(status().isOk())
      .andReturn()
      .getResponse();
    var json = result.getContentAsString();
    var objectMapper = new ObjectMapper();
    var bookingResponse = objectMapper.readValue(json, BookingResponse.class);

    var expected = List.of("3A", "4A");
    assertEquals(expected, bookingResponse.seats());
  }

  @Test
  void check_70_percent_occupancy_empty_seats() {
    List<Seat> seats = new ArrayList<>();
    seats.add(new Seat("1", "A", ""));
    seats.add(new Seat("2", "A", ""));
    seats.add(new Seat("3", "A", ""));
    seats.add(new Seat("4", "A", ""));
    seats.add(new Seat("1", "B", ""));
    seats.add(new Seat("2", "B", ""));
    seats.add(new Seat("3", "B", ""));
    seats.add(new Seat("4", "B", ""));
    seats.add(new Seat("1", "C", ""));
    seats.add(new Seat("2", "C", ""));

    BookingDomain bookingDomain = new BookingDomain();
    assertFalse(bookingDomain.isGlobalOccupancyOver70(seats, 4));
  }

  @Test
  void check_70_percent_occupancy_occupied_seats() {
    List<Seat> seats = new ArrayList<>();
    seats.add(new Seat("1", "A", "fezke"));
    seats.add(new Seat("2", "A", "fzefez"));
    seats.add(new Seat("3", "A", "dfvg"));
    seats.add(new Seat("4", "A", ""));
    seats.add(new Seat("1", "B", ""));
    seats.add(new Seat("2", "B", ""));
    seats.add(new Seat("3", "B", ""));
    seats.add(new Seat("4", "B", ""));
    seats.add(new Seat("1", "C", ""));
    seats.add(new Seat("2", "C", ""));

    BookingDomain bookingDomain = new BookingDomain();
    assertFalse(bookingDomain.isGlobalOccupancyOver70(seats, 4));
  }
}
