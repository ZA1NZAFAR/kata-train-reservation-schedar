package fr.arolla.trainreservation;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
  @Bean
  public ServiceClient serviceClient() {
    return new RestClient();
  }
}
