package com.mos.eboot.admin.config;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.mos.eboot.tools.controller.GlobalDefaultExceptionHandler;
import com.mos.eboot.tools.fastjson.GeneralFastJsonHttpMessageConverter;
import feign.Request;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小尘哥
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
	private MyPropsConstants propsConstants;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        GeneralFastJsonHttpMessageConverter fastJsonHttpMessageConverter = new GeneralFastJsonHttpMessageConverter();
        converters.add(fastJsonHttpMessageConverter);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        if (thymeleafViewResolver != null) {
            Map<String, Object> vars = new HashMap<>(8);
            vars.put("uploadPath", propsConstants.getUploadPath());
            vars.put("defaultImageViewPath", propsConstants.getImageViewPath());
            thymeleafViewResolver.setStaticVariables(vars);
        }
    }

    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    @Bean
    public ServletRegistrationBean kaptchaServlet() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha.jpg");
        servlet.addInitParameter("kaptcha.border", "yes");
        servlet.addInitParameter("kaptcha.border.color", "105,179,90");
        servlet.addInitParameter("kaptcha.textproducer.font.color", "blue");
        servlet.addInitParameter("kaptcha.image.width", "250");
        servlet.addInitParameter("kaptcha.textproducer.font.size", "70");
        servlet.addInitParameter("kaptcha.image.height", "90");
        servlet.addInitParameter("kaptcha.textproducer.char.length", "4");
        servlet.addInitParameter("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.FishEyeGimpy");
        servlet.addInitParameter("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        return servlet;
    }


    @Bean
    Request.Options feignOptions() {
        return new Request.Options(300 * 1000, 300 * 1000);
    }

	@Bean
	public GlobalDefaultExceptionHandler exceptionHandler(){
		return new GlobalDefaultExceptionHandler();
	}
}
