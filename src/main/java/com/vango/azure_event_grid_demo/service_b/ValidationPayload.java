package com.vango.azure_event_grid_demo.service_b;

import lombok.Data;

@Data
public class ValidationPayload {
  String validationCode;
  String validationUrl;
}
