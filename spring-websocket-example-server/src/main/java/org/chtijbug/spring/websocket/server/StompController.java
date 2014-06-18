package org.chtijbug.spring.websocket.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class StompController {

    @MessageMapping("/stomp")
    @SendTo("/topic/greetings")
    public String handle(String greeting) {
        return "[" + new Date() + ": " + greeting;
    }
}
