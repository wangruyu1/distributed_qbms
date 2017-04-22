package cn.qtech.websocket;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WsController {
    @SendTo("/topic/getResponse")//2
    @Scheduled(fixedRate = 5000)
    public WiselyResponse say() throws Exception {
        return new WiselyResponse("Welcome...");
    }
}