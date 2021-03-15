package com.mos.eboot.consumer;

import com.mos.eboot.consumer.config.RabbitConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 小尘哥
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties({RabbitConstants.class})
public class ConsumerApplicationApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationApp.class, args);
    }
}
