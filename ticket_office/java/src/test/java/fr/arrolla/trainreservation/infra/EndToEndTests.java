package fr.arrolla.trainreservation.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.arrolla.trainreservation.FakeServiceClient;
import fr.arrolla.trainreservation.Helpers;
import fr.arrolla.trainreservation.domain.BookingRequest;
import fr.arrolla.trainreservation.domain.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@Import(TestConfig.class)
class EndToEndTests {
  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
    MediaType.APPLICATION_JSON.getType(),
    MediaType.APPLICATION_JSON.getSubtype(),
    StandardCharsets.UTF_8
  );
  final String trainId = "express_2000";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private FakeServiceClient fakeServiceClient;

  @Test
  void contextLoads() {
  }

  @BeforeEach
  void resetClient() {
    fakeServiceClient.reset(trainId);
  }

  @Test
  void reserveFourSeatsFromEmptyTrain() throws Exception {
    var train = Helpers.makeEmptyTrain();
    fakeServiceClient.setTrain(train);

    var request = new BookingRequest(trainId, 4);
    var mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(request);
    String url = "http://127.0.0.1:8083/reserve";

    var result = mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
        .content(requestJson))
      .andExpect(status().isOk());
  }

  public static class BookingReferenceClientTests {
    @Test
    void canGetSeveralUniqueBookingReferences() {
      HttpServiceClient client = new HttpServiceClient();
      String first = client.getNewBookingReference();
      String second = client.getNewBookingReference();
      assertNotEquals(first, second);
    }
  }

  public static class HttpServiceClientTests {
    @Test
    void canResetExistingTrain() {
      var client = new HttpServiceClient();
      client.reset("express_2000");
    }

    @Test
    void canGetTrainData() {
      var client = new HttpServiceClient();
      client.getTrain("express_2000");
    }

    @Test
    void canMakeReservation() {
      var client = new HttpServiceClient();
      var seats = List.of(new String[]{"1A", "2A"});
      var reservation = new Reservation("express_2000", "abc123def", seats);
      client.makeReservation(reservation);
    }
  }


}
