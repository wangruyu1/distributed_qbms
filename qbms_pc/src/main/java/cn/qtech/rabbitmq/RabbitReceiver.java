package cn.qtech.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

//@Component
public class RabbitReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitReceiver.class);

//    @StreamListener(Barista.INPUT)
    public void receiver(Message<Object> message) {
        Object obj = message.getPayload();
        LOGGER.info("收到消息:" + obj.toString());
    }
}  

