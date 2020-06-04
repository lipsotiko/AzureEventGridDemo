package com.vango.azure_event_grid_demo.service_b;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventService {

  private final ObjectMapper mapper;

  public EventService(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  ValidationResponse getValidationResponse(AzureEvent azureEvent) {
    if (azureEvent.isSubscriptionValidationEvent()) {
      ValidationPayload validationPayload = null;
      try {
        byte[] bytes = mapper.writeValueAsBytes(azureEvent.getData());
        validationPayload = mapper.readValue(bytes, ValidationPayload.class);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return new ValidationResponse(validationPayload.getValidationCode());
    }

    return null;
  }

}
