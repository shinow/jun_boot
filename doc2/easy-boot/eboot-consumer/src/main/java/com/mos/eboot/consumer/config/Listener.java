package com.mos.eboot.consumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author 小尘哥
 */
@Configuration
@RabbitListener(queues = RabbitConstants.QUEUE)
public class Listener {

    /** 设置交换机类型  */
    @Bean
    public DirectExchange defaultExchange() {
        /**
         * DirectExchange:按照routingkey分发到指定队列
         * TopicExchange:多关键字匹配
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
         * HeadersExchange ：通过添加属性key-value匹配
         */
        return new DirectExchange(RabbitConstants.EXCHANGE);
    }

    @Bean
    public Queue fooQueue() {
        return new Queue(RabbitConstants.QUEUE);
    }

    @Bean
    public Binding binding() {
        /** 将队列绑定到交换机 */
        return BindingBuilder.bind(fooQueue()).to(defaultExchange()).with(RabbitConstants.ROUTINGKEY);
    }

    @RabbitHandler
    public void process(@Payload String foo) {
        System.out.println("Listener: " + foo);
    }
}
