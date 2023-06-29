package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.InMemoryRepository;
import fr.arolla.trainreservation.ticket_office.domain.TrainRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestConfig {

  @Bean
  TrainRepository trainRepository() {
    return new InMemoryRepository();
  }


}
