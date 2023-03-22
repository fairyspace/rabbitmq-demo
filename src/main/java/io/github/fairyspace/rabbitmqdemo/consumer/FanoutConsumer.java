package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanoutConsumer {
    @RabbitListener(bindings=
        @QueueBinding(
                value =@Queue,
                exchange = @Exchange(name = "exfanout",type = "fanout")))
    public void  receive(String message){
        log.info("fan方法一接收到："+message);
    }


    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue,
            exchange = @Exchange(name = "exfanout",type = "fanout")))
    public void  receive2(String message){
        log.info("fan方法二接收到："+message);
    }
}
