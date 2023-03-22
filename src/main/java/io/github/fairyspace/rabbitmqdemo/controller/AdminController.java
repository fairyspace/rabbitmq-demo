package io.github.fairyspace.rabbitmqdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AdminController {
    @Autowired
    private RabbitAdmin rabbitAdmin;
    @PostMapping("/deleteQueue")
    public void deleteQueue(String queueName) {
        rabbitAdmin.deleteQueue(queueName);
    }


    /*删除交换机，对应的队列也会删除*/
    @PostMapping("/deleteExchange")
    public void deleteExchange(String exchangeName) {
        rabbitAdmin.deleteExchange(exchangeName);
    }
}
