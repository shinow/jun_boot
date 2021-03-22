package com.mos.eboot.web;

import com.mos.eboot.web.config.RabbitConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author 小尘哥
 */
@SpringBootApplication
@EnableConfigurationProperties({RabbitConstants.class})
public class WebApplicationApp {
    public static void main(String[] args) {
        SpringApplication.run(WebApplicationApp.class, args);
    }
}
