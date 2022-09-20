package fr.arrolla.trainreservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.arrolla.trainreservation.domain.BookingRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
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

}
