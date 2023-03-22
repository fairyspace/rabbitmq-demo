package io.github.fairyspace.rabbitmqdemo.consumer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.core.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class HeaderConsumer {

    //@Argument(name = "x-match", value = "all")
    //表示要全部匹配才能路由到队列
    @SneakyThrows
    @RabbitListener(bindings=
    @QueueBinding(
            value =@Queue("headers_queue"),
            arguments = {@Argument(name = "x-match", value = "all"),
                    @Argument(name = "aa", value = "1111"),
                    @Argument(name = "bb", value = "2222")},
            exchange = @Exchange(value = "exheader",type = "headers")))
    public void  receive(Message message, @Payload String messagestr, @Headers Map<String, Object> headers){
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        System.out.println("队列收到消息：" + new String(message.getBody(), contentType));
    }
}
