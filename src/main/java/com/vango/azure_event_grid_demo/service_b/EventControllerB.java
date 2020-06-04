package com.vango.azure_event_grid_demo.service_b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/service-b/event")
public class EventControllerB {

  @Value("${service-b.subscription.token}")
  private String token;

  private final EventService eventService;

  public EventControllerB(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping("/listener")
  public ResponseEntity<ValidationResponse> eventListener(@RequestBody List<AzureEvent> azureEvents, @RequestParam String token) {
    if (!this.token.equals(token)) return ResponseEntity.badRequest().build();
    ValidationResponse validationResponse = validationHandshake(azureEvents);
    if (validationResponse != null) return ResponseEntity.ok(validationResponse);

    // TODO: Do something with the event...
    System.out.println(azureEvents.get(0).getData());

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

//  private void validationHandshake(List<com.vango.azure_event_grid_demo.AzureEvent> azureEvents) {
//    List<com.vango.azure_event_grid_demo.AzureEvent> validationEvents =
//      azureEvents.stream().filter(e -> e.getEventType().equals("Microsoft.EventGrid.SubscriptionValidationEvent")).collect(Collectors.toList());
//    if (validationEvents.size() > 0) {
//      log.info("Validation handshake was received from Azure");
//      String validationUrl = validationEvents.get(0).getData().getValidationUrl();
//      try {
//        HttpURLConnection con = (HttpURLConnection) new URL(validationUrl).openConnection();
//        con.getInputStream();
//        log.info("Manual validation request response {}", new String(IOUtils.toByteArray(con.getInputStream())));
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }
}
