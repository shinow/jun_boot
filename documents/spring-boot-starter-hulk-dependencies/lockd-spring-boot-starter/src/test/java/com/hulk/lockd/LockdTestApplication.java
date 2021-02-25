package com.hulk.lockd;

import com.hulk.lockd.annotation.EnableLockdConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hulk on 2018/12/31.
 */
@SpringBootApplication
@EnableLockdConfig
public class LockdTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockdTestApplication.class, args);
    }

}
