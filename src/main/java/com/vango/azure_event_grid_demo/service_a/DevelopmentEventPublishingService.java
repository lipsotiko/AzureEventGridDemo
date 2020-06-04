package com.vango.azure_event_grid_demo.service_a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("development")
public class DevelopmentEventPublishingService implements EventPublishingService {

  @Autowired
  private SimpMessagingTemplate messageTemplate;

  @Override
  public void publish(List<AzureEvent> events) {
    messageTemplate.convertAndSend(
      "/topic/messages", new AzureEvent("Vango.Event.HelloWorld", "1.0", events.get(0).getData())
    );
  }
}
