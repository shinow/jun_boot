package com.ruoyi.project.wxapi.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AddInterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new JWTLoginInterceptor())
        .addPathPatterns("/wxapi/**")
        .excludePathPatterns("/wxapi/user/reg")
        .excludePathPatterns("/wxapi/user/login")
        .excludePathPatterns("/wxapi/pay/**");
    }

}