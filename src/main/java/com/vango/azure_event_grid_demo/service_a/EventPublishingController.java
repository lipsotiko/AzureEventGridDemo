package com.vango.azure_event_grid_demo.service_a;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/service-a/event")
public class EventPublishingController {

  private final EventPublishingService eventPublishingService;

  public EventPublishingController(EventPublishingService eventPublishingService) {
    this.eventPublishingService = eventPublishingService;
  }

  @PostMapping("/create")
  public void create(@RequestBody HelloWorld helloWorld) {
    log.info("Sending HelloWorld event to Azure Event Grid: {}", helloWorld);
    AzureEvent event = new AzureEvent("Vango.Event.HelloWorld", "1.0", helloWorld);
    eventPublishingService.publish(Collections.singletonList(event));
  }

}
