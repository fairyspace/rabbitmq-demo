package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
public class WorkConsumer2 {

    @SneakyThrows
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",queuesToDeclare = @Queue("work"))
    public void  receive1(String message){
        TimeUnit.SECONDS.sleep(1);
        log.info("方法一接收到消息:>>>"+message);
    }


    @SneakyThrows
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",queuesToDeclare = @Queue("work"))
    public void  receive2(String message){
        TimeUnit.SECONDS.sleep(4);
        log.info("方法二接收到消息:>>>"+message);
    }
}
