package io.github.fairyspace.rabbitmqdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/simple2")
    public void send(String routeKey, String message) {
        /*消息ID*/
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        correlationData.getFuture().addCallback(success -> {
            // 判断结果
            if (success.isAck()) {
                // ACK
                log.info("消息成功投递到交换机！消息ID: {}", correlationData.getId());
            } else {
                // NACK
                log.error("消息投递到交换机失败！消息ID：{}，原因：{}", correlationData.getId(), success.getReason());
                // 重发消息
            }
        },failure -> {
            // 记录日志
            log.error("消息发送异常, ID:{}, 原因{}", correlationData.getId(), failure.getMessage());
            // 可以重发消息
        });

        rabbitTemplate.convertAndSend("",routeKey, message,correlationData);
    }



    @PostMapping("/simple")
    public void simpleSend(String routeKey, String message) {
        /*消息ID*/
        rabbitTemplate.convertAndSend(routeKey, message);
    }


    @PostMapping("/ttl")
    public void ttlSend(String exchange,String routeKey, String message) {
        /*消息ID*/
       log.info("ttl发送消息");
        rabbitTemplate.convertAndSend(exchange,routeKey, message);
    }


    @PostMapping("/ttl2")
    public void ttlSend2(String exchange,String routeKey, String message) {

        Message message2 = MessageBuilder
                .withBody("hello, ttl messsage".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                //设置超时时间为5s
                .setExpiration("5000")
                .build();

        /*消息ID*/

        rabbitTemplate.convertAndSend("ttl.direct", "ttl", message2);
        log.info("ttl发送消息");
    }

    @PostMapping("/work")
    public void workSend(String routeKey, String message) {
        for (int i = 0; i < 1; i++) {
            rabbitTemplate.convertAndSend(routeKey, "第"+i+"条信息:"+message);
        }

    }


    @PostMapping("/fanout")
    public void fanSend(String exchange, String message) {
            String routingKey="";
            rabbitTemplate.convertAndSend(exchange, "",message);
    }

    @PostMapping("/direct")
    public void directSend(String exchange, String routingKey,String message) {
        //路由模式
        rabbitTemplate.convertAndSend(exchange, routingKey,message);
    }


    @PostMapping("/topic")
    public void topicSend(String exchange, String routingKey,String message) {
        //路由模式
        rabbitTemplate.convertAndSend(exchange, routingKey,message);
    }


    @PostMapping("/head")
    public void headSend(String exchange, String routingKey,String rawmessage) {
        //请求头模式
        MessageProperties messageProperties = new MessageProperties();
        //消息持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");
        Map<String, String> map = new HashMap<>();
        map.put("aa", "1111");
        map.put("bb", "2222");
        messageProperties.getHeaders().putAll(map);
        Message message = new Message(rawmessage.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(exchange, routingKey,message);
    }
}
