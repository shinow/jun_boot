package com.mos.eboot.admin;

import com.mos.eboot.admin.config.MyPropsConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 小尘哥
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({MyPropsConstants.class})
public class AdminApplicationApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplicationApp.class, args);
    }
}
