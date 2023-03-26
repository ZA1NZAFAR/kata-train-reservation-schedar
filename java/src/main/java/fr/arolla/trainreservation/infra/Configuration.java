package fr.arolla.trainreservation.infra;

import fr.arolla.trainreservation.domain.ServiceClient;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
  @Bean
  public ServiceClient serviceClient() {
    return new RestClient();
  }
}
