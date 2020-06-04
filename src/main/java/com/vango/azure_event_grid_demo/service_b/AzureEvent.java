package com.vango.azure_event_grid_demo.service_b;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AzureEvent {
  private String id;
  private String subject;
  private String eventType;
  private String eventTime;
  private String dataVersion;
  private Object data;

  Boolean isSubscriptionValidationEvent() {
    return "Microsoft.EventGrid.SubscriptionValidationEvent".equals(eventType);
  }

}
