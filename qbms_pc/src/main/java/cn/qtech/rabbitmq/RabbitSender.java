package cn.qtech.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitSender.class);

    private Source source;

    // 发送消息
//    @Scheduled(fixedDelay = 5000)
//    @Async
    public String sendMessage(Object message) {
        try {
//            ObjectMapper objectMapper = new ObjectMapper();
            source.user().send(MessageBuilder.withPayload(message).build());
            LOGGER.info("manager发送消息:" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}  
