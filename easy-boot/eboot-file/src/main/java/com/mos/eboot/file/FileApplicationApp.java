package com.mos.eboot.file;

import com.mos.eboot.file.config.MyPropsConstants;
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
@EnableConfigurationProperties({MyPropsConstants.class})
public class FileApplicationApp {
    public static void main(String[] args) {
        SpringApplication.run(FileApplicationApp.class, args);
    }
}
