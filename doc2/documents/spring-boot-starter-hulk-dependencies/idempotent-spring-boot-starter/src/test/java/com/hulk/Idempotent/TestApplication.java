package com.hulk.Idempotent;

import com.hulk.Idempotent.annotation.EnableIdempotentConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hulk on 2018/12/31.
 */
@SpringBootApplication
@EnableIdempotentConfig
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
