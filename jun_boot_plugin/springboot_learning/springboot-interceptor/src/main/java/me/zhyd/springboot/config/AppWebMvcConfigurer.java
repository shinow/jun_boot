package me.zhyd.springboot.config;

import me.zhyd.springboot.interceptor.CustomInterceptor1;
import me.zhyd.springboot.interceptor.CustomInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new CustomInterceptor1())
                .excludePathPatterns("/error1/**")
                .addPathPatterns("/random1/**");
        registry.addInterceptor(new CustomInterceptor2())
                .excludePathPatterns("/error1/**")
                .addPathPatterns("/random2/**");
        super.addInterceptors(registry);
    }

}
