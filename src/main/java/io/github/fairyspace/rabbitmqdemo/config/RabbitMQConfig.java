package io.github.fairyspace.rabbitmqdemo.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //破坏的重试机制
    //@Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // MANUAL 手动确认
        // AUTO  消费完自动确认（Spring 确认）
        // NONE  消息分配后就确认（rabbitmq 确认）
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 拒绝策略,true 回到队列 false 丢弃，默认为 true
        factory.setDefaultRequeueRejected(true);
        // 默认的 PrefetchCount是 250,能者多劳模式
        factory.setPrefetchCount(0);
        return factory;
    }

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }
}
