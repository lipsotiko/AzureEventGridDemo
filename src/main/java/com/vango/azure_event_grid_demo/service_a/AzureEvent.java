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
  String id;
  String subject;
  String eventType;
  String eventTime;
  String dataVersion;

  public AzureEvent(String subject, String eventType, String dataVersion) {
    this.id = UUID.randomUUID().toString();
    this.subject = subject;
    this.eventType = eventType;
    this.dataVersion = dataVersion;
    this.eventTime = LocalDateTime.now().toString();
  }
}
