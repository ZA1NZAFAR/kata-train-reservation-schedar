package fr.arrolla.trainreservation;

import fr.arrolla.trainreservation.domain.ServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class TestConfig {

  @Bean
  ServiceClient serviceClient() {
    return new FakeServiceClient();
  }
}
