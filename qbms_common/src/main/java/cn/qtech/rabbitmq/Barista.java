package cn.qtech.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {
    String OUTPUT = "output_channel";
    String INPUT = "input_channel";

    @Input(Barista.INPUT)
    SubscribableChannel input();

    @Output(Barista.OUTPUT)
    MessageChannel output();

}  