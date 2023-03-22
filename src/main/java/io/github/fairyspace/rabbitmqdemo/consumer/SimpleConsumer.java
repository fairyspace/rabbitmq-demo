package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queuesToDeclare=@Queue("simple"))
public class SimpleConsumer {
    @RabbitHandler
    public void  receive(String message){


        log.info(message);

        System.err.println(1/0);
    }
}
