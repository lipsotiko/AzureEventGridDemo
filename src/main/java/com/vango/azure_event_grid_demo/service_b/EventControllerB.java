package com.vango.azure_event_grid_demo.service_b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/service-b/event")
public class EventControllerB {

  @Value("${service-b.subscription.token}")
  private String token;

  private final EventService eventService;
  private final SimpMessagingTemplate messageTemplate;

  public EventControllerB(EventService eventService, SimpMessagingTemplate messageTemplate) {
    this.eventService = eventService;
    this.messageTemplate = messageTemplate;
  }

  @PostMapping("/listener")
  public ResponseEntity<ValidationResponse> eventListener(@RequestBody List<AzureEvent> azureEvents, @RequestParam String token) {
    // Validate requests from Azure...otherwise, random people can mess with us.
    if (!this.token.equals(token)) return ResponseEntity.badRequest().build();

    // Make sure Azure knows we want this to happen
    ValidationResponse validationResponse = validationHandshake(azureEvents);
    if (validationResponse != null) return ResponseEntity.ok(validationResponse);

    // Send events to the UI; or save them to a db...
    azureEvents.forEach(e -> messageTemplate.convertAndSend("/topic/messages", e));

    return ResponseEntity.ok().build();
  }

  private ValidationResponse validationHandshake(List<AzureEvent> azureEvents) {
    AzureEvent azureEvent = azureEvents.stream().filter(AzureEvent::isSubscriptionValidationEvent).findAny().orElse(null);
    if (azureEvent != null) {
      log.info("Received validation handshake from Azure {}", azureEvent);
      return eventService.getValidationResponse(azureEvent);
    }

    return null;
  }

}
