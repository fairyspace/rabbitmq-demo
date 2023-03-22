package io.github.fairyspace.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LongTimeMessageConfig {


    @Bean
    public DirectExchange ttlExchange(){
        return new DirectExchange("ttl.direct");
    }

    @Bean
    public Queue ttlQueue(){
        return QueueBuilder.durable("ttl.queue")
                // 设置队列的超时时间，10秒
                .ttl(10000)
                .deadLetterExchange("error.direct")
                .deadLetterRoutingKey("error")
                .build();
    }

    @Bean
    public Binding ttlBinding(){
        return BindingBuilder.bind(ttlQueue()).to(ttlExchange()).with("ttl");
    }
}
