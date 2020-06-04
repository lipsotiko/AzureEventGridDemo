package com.vango.azure_event_grid_demo.service_a;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/service-a/event")
public class EventControllerA {

  @Value("${service-a.topic.endpoint}")
  String topicEndpoint;

  @Value("${service-a.topic.access-key}")
  String accessKey;

  @PostMapping("/create")
  public void create(@RequestBody HelloWorld helloWorld) {
    log.info("Sending HelloWorld event to Azure Event Grid: {}", helloWorld);
    AzureEvent event = new AzureEvent("Vango.Event.HelloWorld", "1.0", helloWorld);
    publish(Collections.singletonList(event));
  }

  private void publish(List<AzureEvent> events) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("aeg-sas-key", accessKey);
    HttpEntity<List<AzureEvent>> httpEntity = new HttpEntity<>(events, httpHeaders);
    restTemplate.exchange(topicEndpoint, HttpMethod.POST, httpEntity, Void.class);
  }
}
