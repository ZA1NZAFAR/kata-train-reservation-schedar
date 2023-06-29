package fr.arolla.trainreservation.ticket_office.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.arolla.trainreservation.ticket_office.domain.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
  void reserveFourSeatsFromEmptyTrain() throws Exception {
    final String trainId = "express_2000";
    var restTemplate = new RestTemplate();
    restTemplate.postForObject("http://127.0.0.1:8081" + "/reset/" + trainId, null, String.class);

    var request = new BookingRequest(trainId, 4);
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
    var booking = objectMapper.readValue(json, Booking.class);

    var expected = List.of("1A", "2A", "3A", "4A");
    assertEquals(expected, booking.seats());
  }

  @Test
  void reserveFourAdditionalSeats() throws Exception {
    // Reset
    final String trainId = "express_2000";
    var restTemplate = new RestTemplate();
    restTemplate.postForObject("http://127.0.0.1:8081" + "/reset/" + trainId, null, String.class);

    // Book 4 seats
    var request = new BookingRequest(trainId, 4);
    var mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(request);
    String url = "http://127.0.0.1:8083/reserve";

    mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
        .content(requestJson))
      .andExpect(status().isOk());

    // Book 4 additional seats
    var result = mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
        .content(requestJson))
      .andExpect(status().isOk())
      .andReturn()
      .getResponse();


    var json = result.getContentAsString();
    var objectMapper = new ObjectMapper();
    var booking = objectMapper.readValue(json, Booking.class);

    var expected = List.of("1B", "2B", "3B", "4B");
    assertEquals(expected, booking.seats());
  }
  
}
