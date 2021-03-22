package org.springrain.rocketmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration("configuration-rocketMQConfig")
public class RocketMqConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RocketmqProperties rocketmqProperties;

    @Bean("rocketMqClient")
    public RocketMqClient getRocketMqClient() {
        RocketMqClient rocketMqClient = null;
        try {
            System.out.println("开始创建RocketMqClient...");
            rocketMqClient = new RocketMqClient(rocketmqProperties);
            // 创建普通的生产者
            rocketMqClient.createProducer();
            // 创建带事务的生产者
            rocketMqClient.createTransactionProducer();


            System.out.println("开始registerConsumerListener...");
            rocketMqClient.registerConsumerListener();
            System.out.println("registerConsumerListener成功...");
            System.out.println("RocketMqClient创建成功...");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return rocketMqClient;
    }
}
