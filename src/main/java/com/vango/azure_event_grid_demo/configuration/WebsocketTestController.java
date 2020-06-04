package com.vango.azure_event_grid_demo.configuration;

import com.vango.azure_event_grid_demo.service_a.AzureEvent;
import com.vango.azure_event_grid_demo.service_a.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebsocketTestController {

  @Autowired
  private SimpMessagingTemplate messageTemplate;

  @GetMapping("/api/test/ws")
  public void testWs() {
    messageTemplate.convertAndSend(
      "/topic/messages", new AzureEvent("Vango.Event.HelloWorld", "1.0", new HelloWorld("cool"))
    );
  }
}
