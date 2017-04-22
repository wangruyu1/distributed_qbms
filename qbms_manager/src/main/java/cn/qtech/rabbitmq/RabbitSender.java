package cn.qtech.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitSender.class);

    @Autowired
    private Source source;

    // 发送消息
//    @Scheduled(fixedDelay = 5000)
    public boolean sendMessage(Object message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            source.manager_output().send(MessageBuilder.withPayload(objectMapper.writeValueAsString(message)).build());
            LOGGER.info("发送消息...");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}  
