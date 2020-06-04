package com.vango.azure_event_grid_demo.service_a;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Profile("production")
public class ProductionEventPublishingService implements EventPublishingService {

  @Value("${service-a.topic.endpoint}")
  String topicEndpoint;

  @Value("${service-a.topic.access-key}")
  String accessKey;

  private final RestTemplate restTemplate;

  public ProductionEventPublishingService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public void publish(List<AzureEvent> events) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("aeg-sas-key", accessKey);
    HttpEntity<List<AzureEvent>> httpEntity = new HttpEntity<>(events, httpHeaders);
    restTemplate.exchange(topicEndpoint, HttpMethod.POST, httpEntity, Void.class);
  }
}
