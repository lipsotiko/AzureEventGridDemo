package com.vango.azure_event_grid_demo.service_b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vango.azure_event_grid_demo.service_b.event.SubscriptionValidation;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventService {

  private final ObjectMapper mapper;

  public EventService(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  ValidationResponse getValidationResponse(AzureEvent azureEvent) {
    SubscriptionValidation subscriptionValidation = null;
    try {
      byte[] bytes = mapper.writeValueAsBytes(azureEvent.getData());
      subscriptionValidation = mapper.readValue(bytes, SubscriptionValidation.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ValidationResponse(subscriptionValidation.getValidationCode());
  }

}
