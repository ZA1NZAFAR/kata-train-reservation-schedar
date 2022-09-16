package fr.arrolla.trainreservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.arrolla.trainreservation.domain.BookingRequest;
import fr.arrolla.trainreservation.infra.TrainDataClient;
import fr.arrolla.trainreservation.infra.TrainDataParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketOfficeTests {
  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
    MediaType.APPLICATION_JSON.getType(),
    MediaType.APPLICATION_JSON.getSubtype(),
    StandardCharsets.UTF_8
  );

  @Autowired
  private MockMvc mockMvc;

  @Test
  void contextLoads() {
  }

  @Test
  void reserveFourSeatsFromEmptyTrain() throws Exception {
    final String trainId = "express_2000";
    var client = new TrainDataClient();
    client.reset(trainId);

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

    var body = result.getContentAsString();
    var parser = new TrainDataParser();
    var newTrainData = parser.parse(body);

    assertNotNull(newTrainData);

    var seatsWithReservation = newTrainData.seats().stream().filter(seat -> seat.bookingReference() != null).toList();
    assertEquals(4, seatsWithReservation.size());
    var seatNumbers = seatsWithReservation.stream().map(seat -> seat.id().toString()).sorted().toList();
    var expected = List.of("1A", "2A", "3A", "4A");
    assertEquals(expected, seatNumbers);

  }

}
