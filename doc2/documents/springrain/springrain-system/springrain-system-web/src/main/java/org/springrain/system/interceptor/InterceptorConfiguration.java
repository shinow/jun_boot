package org.springrain.system.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springrain.frame.util.GlobalStatic;

import javax.annotation.Resource;

@Configuration("interceptorConfiguration")
public class InterceptorConfiguration implements WebMvcConfigurer {


    @Resource
    private JwtInterceptor jwtInterceptor;


    /**
     * 配置拦截器
     *
     * @param registry
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //JWT全局拦截认证
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns(GlobalStatic.excludePathPatterns);

        //  其他的跳转到 500错误

    }


}
