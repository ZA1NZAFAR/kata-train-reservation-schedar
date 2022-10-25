package fr.arolla.trainreservation.infra;

import fr.arolla.trainreservation.domain.ServiceClient;
import fr.arolla.trainreservation.FakeServiceClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestConfig {

  @Bean
  ServiceClient serviceClient() {
    return new FakeServiceClient();
  }


}
