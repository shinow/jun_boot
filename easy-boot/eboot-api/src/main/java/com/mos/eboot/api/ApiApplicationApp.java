package com.mos.eboot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 小尘哥
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ApiApplicationApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplicationApp.class, args);
    }
}
