package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.FakeServiceClient;
import fr.arolla.trainreservation.ticket_office.domain.ServiceClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestConfig {

  @Bean
  ServiceClient serviceClient() {
    return new FakeServiceClient();
  }


}
