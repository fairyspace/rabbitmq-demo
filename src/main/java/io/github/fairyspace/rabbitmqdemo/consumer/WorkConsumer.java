package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void  receive1(String message){
        System.err.println(1/0);
        log.info("方法一接收到消息:>>>"+message);
    }


    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void  receive2(String message){
        System.err.println(1/0);
        log.info("方法二接收到消息:>>>"+message);
    }
}
