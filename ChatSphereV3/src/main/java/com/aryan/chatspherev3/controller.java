package com.aryan.chatspherev3;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @MessageMapping("/message")
    @SendTo("/topic/return-to")
    public Message getMessage(@RequestBody Message message) {
        return message;
    }
}
