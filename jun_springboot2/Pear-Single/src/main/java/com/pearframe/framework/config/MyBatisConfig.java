package com.pearframe.framework.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Configuration: 注解该类是一个配置文件
 * @EnableTransactionManagement: 开启事务注解
 * */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig {

    /**
     * Describe: MyBatis Plus 分页插件
     * */
    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return new PaginationInterceptor();
    }
}
