package com.vango.azure_event_grid_demo.service_a;

import lombok.Data;

@Data
public class HelloWorldEvent extends AzureEvent {
  Object data;

  public HelloWorldEvent(String subject, String eventType, String dataVersion, Object data) {
    super(subject, eventType, dataVersion);
    this.data = data;
  }
}
