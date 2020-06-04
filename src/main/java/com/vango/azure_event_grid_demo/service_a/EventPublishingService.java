package com.vango.azure_event_grid_demo.service_a;

import java.util.List;

public interface EventPublishingService {
  void publish(List<AzureEvent> events);
}
