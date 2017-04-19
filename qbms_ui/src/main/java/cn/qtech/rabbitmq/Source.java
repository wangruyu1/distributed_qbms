package cn.qtech.rabbitmq;

import cn.qtech.constant.SourceChannelName;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wangruyu
 * @since 2017/4/17-19:12
 */
public interface Source {
    @Input(SourceChannelName.UI)
    SubscribableChannel ui();

    @Output(SourceChannelName.AC)
    MessageChannel ac();

    @Output(SourceChannelName.MANAGER)
    MessageChannel manager();

    @Output(SourceChannelName.USER)
    MessageChannel user();
}
