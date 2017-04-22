package cn.qtech.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wangruyu
 * @since 2017/4/17-19:12
 */
public interface Source {
    String MANAGER_INPUT = "qbms_manager_input";
    String MANAGER_OUTPUT = "qbms_manager_output";

    @Input(MANAGER_INPUT)
    SubscribableChannel manager_input();

    @Output(MANAGER_OUTPUT)
    MessageChannel manager_output();
}
