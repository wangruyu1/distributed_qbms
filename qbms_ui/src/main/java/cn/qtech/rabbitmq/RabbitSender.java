package cn.qtech.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;

//@Component
public class RabbitSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitSender.class);
  
    private Barista source;
  
    // 发送消息
//    @Scheduled(fixedDelay = 5000)
//    @Async
    public String sendMessage(){  
        try{  
        String message = "hello...";
            source.output().send(MessageBuilder.withPayload(message).build());
            LOGGER.info("发送消息...");
        }catch (Exception e){
            e.printStackTrace();  
        }  
        return null;  
    }  
}  
