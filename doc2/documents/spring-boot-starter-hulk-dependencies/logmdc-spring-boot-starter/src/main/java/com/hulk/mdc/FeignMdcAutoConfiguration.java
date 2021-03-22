package com.hulk.mdc;



import com.hulk.mdc.annotation.EnableFeignMdcConfig;
import com.hulk.mdc.interceptor.FeignMdcInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created hulk  on 2019/05/28.
 * Content :mdc
 */
@Configuration
@ConditionalOnBean(annotation = EnableFeignMdcConfig.class)
public class FeignMdcAutoConfiguration {


    @Bean
    public RequestInterceptor feignMdcInterceptor() {
        return new FeignMdcInterceptor();
    }


}
