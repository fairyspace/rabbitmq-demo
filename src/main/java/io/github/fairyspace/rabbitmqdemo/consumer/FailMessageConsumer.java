package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FailMessageConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "error.queue", durable = "true"),
            exchange = @Exchange(name = "error.direct"),
            key = "error"
    ))
    public void listenDlQueue(String msg) {
        log.info("消费者接收到了error.queue 的延迟消息：{}", msg);
    }
}
