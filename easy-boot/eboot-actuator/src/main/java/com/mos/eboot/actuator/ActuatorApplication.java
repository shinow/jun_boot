package com.mos.eboot.actuator;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小尘哥
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class ActuatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }
}
