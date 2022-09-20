package fr.arrolla.trainreservation.infra;

import fr.arrolla.trainreservation.domain.ServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public ServiceClient serviceClient() {
    return new HttpServiceClient();
  }
}
