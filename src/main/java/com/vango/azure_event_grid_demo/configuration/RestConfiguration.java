package com.vango.azure_event_grid_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

  @Bean
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
