package com.jeasyuicn.rbac;

import com.jeasyuicn.rbac.interceptor.LoginInterceptor;
import com.jeasyuicn.rbac.interceptor.RightsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.jeasyuicn.rbac</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年12月21日</li>
 * <li>@author     : ____′↘夏悸</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    RightsInterceptor rightsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //此处表示需要拦截程序的所有请求
        registration.addPathPatterns("/**");
        //排除指定请求不拦截
        registration.excludePathPatterns("/", "/login", "/error");

        //请求的权限过滤器
        InterceptorRegistration rightsRegistration = registry.addInterceptor(rightsInterceptor);

        rightsRegistration.addPathPatterns("/**");
        rightsRegistration.excludePathPatterns("/", "/login", "/error", "/reject", "/menus");
    }
}
