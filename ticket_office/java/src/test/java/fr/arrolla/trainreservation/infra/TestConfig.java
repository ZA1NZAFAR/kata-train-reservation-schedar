package fr.arrolla.trainreservation.infra;

import fr.arrolla.trainreservation.FakeServiceClient;
import fr.arrolla.trainreservation.domain.ServiceClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestConfig {

  @Bean
  ServiceClient serviceClient() {
    return new FakeServiceClient();
  }


}
