package com.vango.azure_event_grid_demo.service_b.event;

import lombok.Data;

@Data
public class SubscriptionValidation {
  String validationCode;
  String validationUrl;
}
