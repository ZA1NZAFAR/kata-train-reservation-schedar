package fr.arolla.trainreservation.infra;

import fr.arolla.trainreservation.infra.controllers.BookingRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
class EndToEndTests {
  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
    MediaType.APPLICATION_JSON.getType(),
    MediaType.APPLICATION_JSON.getSubtype(),
    StandardCharsets.UTF_8
  );
  private final RestClient restClient;
  @Autowired
  private MockMvc mockMvc;

  EndToEndTests() {
    restClient = new RestClient();
  }

  @Test
  void reserve_four_seats_from_empty_train() throws Exception {
    final String trainId = "express_2000";
    restClient.reset(trainId);

    var request = new BookingRequest(trainId, 4);
    restClient.book(request);
  }

  @Test
  void reserve_four_additional_seats() throws Exception {
    final String trainId = "express_2000";
    restClient.reset(trainId);

    var request = new BookingRequest(trainId, 4);
    restClient.book(request);

    request = new BookingRequest(trainId, 4);
    restClient.book(request);
  }
}
