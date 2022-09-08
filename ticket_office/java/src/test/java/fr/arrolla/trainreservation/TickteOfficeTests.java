package fr.arrolla.trainreservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TickteOfficeTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void canCallPing() throws Exception {
		var query = get("http://127.0.0.1:8083/ping");
		var statusOk = status().isOk();
		var response = mockMvc.perform(query).andExpect(statusOk).andReturn().getResponse();
		var body = response.getContentAsString();
		assertEquals("pong", body);
	}

	@Test
	void reserveSeatsFromEmptyTrain() throws Exception {
		var client = new TrainDataClient();
		client.reset();

	}

}
