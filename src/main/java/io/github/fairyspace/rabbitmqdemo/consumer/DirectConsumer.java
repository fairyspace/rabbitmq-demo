package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectConsumer {
    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue,
            key = {"aabbcc","bbcc"},
            exchange = @Exchange(name = "exdirect",type = "direct")))
    public void  receive(String message){
        log.info("direct方法二接收到："+message);
    }


    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue,
            key = {"aabbcc"},
            exchange = @Exchange(name = "exdirect",type = "direct")))
    public void  receive2(String message){
        log.info("direct方法二接收到："+message);
    }
}
