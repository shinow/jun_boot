package com.mos.eboot.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: 朱建柯
 * Date: 2019/1/17
 * Time: 13:37
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EsApp {
    public static void main(String[] args) {
        SpringApplication.run(EsApp.class, args);
    }
}
