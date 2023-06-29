package fr.arolla.trainreservation.ticket_office.infra;

import fr.arolla.trainreservation.ticket_office.domain.TrainRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public TrainRepository serviceClient() {
    return new RestClient();
  }
}
