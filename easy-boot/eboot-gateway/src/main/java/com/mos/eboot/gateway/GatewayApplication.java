package com.mos.eboot.gateway;

import com.mos.eboot.gateway.config.HostAddressKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: 朱建柯
 * Email: hautxxxyzjk@163.com
 * Date: 2019-03-29
 * Time: 0:07
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean("hostAddressKeyResolver")
    public HostAddressKeyResolver hostAddressKeyResolver() {
        return new HostAddressKeyResolver();
    }

}
