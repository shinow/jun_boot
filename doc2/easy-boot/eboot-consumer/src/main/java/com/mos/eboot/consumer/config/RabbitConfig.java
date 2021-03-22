package com.mos.eboot.consumer.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * @author 小尘哥
 */
@Configuration
public class RabbitConfig {

    @Resource
    private RabbitConstants rabbitConstants;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitConstants.getHost());
        connectionFactory.setUsername(rabbitConstants.getUsername());
        connectionFactory.setPassword(rabbitConstants.getPassword());
        connectionFactory.setVirtualHost(rabbitConstants.getVirtualHost());
        /** 如果要进行消息回调，则这里必须要设置为true */
        connectionFactory.setPublisherConfirms(rabbitConstants.getPublisherConfirms());
        return connectionFactory;
    }

    /**
     * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

}
