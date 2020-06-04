package com.vango.azure_event_grid_demo.service_a;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

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

  public AzureEvent(String eventType, String dataVersion, Object data) {
    this.id = UUID.randomUUID().toString();
    this.subject = "AzureEventGridDemo";
    this.eventTime = LocalDateTime.now().toString();
    this.dataVersion = dataVersion;
    this.eventType = eventType;
    this.data = data;
  }
}
