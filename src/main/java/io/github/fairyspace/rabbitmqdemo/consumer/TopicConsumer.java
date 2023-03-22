package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicConsumer {
    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue,
            key = {"aa.bb.cc"},
            exchange = @Exchange(name = "extopic",type = "topic")))
    public void  receive(String message){
        log.info("direct方法一接收到："+message);
    }


    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue,
            key = {"aa.bb.*"},
            exchange = @Exchange(name = "extopic",type = "topic")))
    public void  receive2(String message){
        log.info("direct方法二接收到："+message);
    }

    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue,
            key = {"aa.bb.#"},
            exchange = @Exchange(name = "extopic",type = "topic")))
    public void  receive3(String message){
        log.info("direct方法3接收到："+message);
    }

    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue,
            key = {"aa.#"},
            exchange = @Exchange(name = "extopic",type = "topic")))
    public void  receive4(String message){
        log.info("direct方法4接收到："+message);
    }
}
