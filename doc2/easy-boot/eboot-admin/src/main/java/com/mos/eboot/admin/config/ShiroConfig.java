package com.mos.eboot.admin.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.google.common.collect.Maps;
import com.mos.eboot.admin.config.service.impl.PermissionService;
import com.mos.eboot.admin.config.service.impl.UserService;
import com.mos.eboot.tools.shiro.KaptchaValidateFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.MessageSourceAccessor;

import javax.servlet.Filter;
import java.util.Map;

/**
 * @author 小尘哥
 */
@Configuration
public class ShiroConfig{


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(PermissionService permissionService) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm(permissionService));
        return manager;
    }

    @Bean
    public MethodInvokingFactoryBean setSecurityManager(PermissionService permissionService) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager(permissionService));
        return methodInvokingFactoryBean;
    }

    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public UserRealm userRealm(PermissionService permissionService) {
        UserRealm userRealm = new UserRealm(permissionService);
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }

    @Bean
    public FormAuthenticationFilter authcFilter(@Qualifier("messageSourceAccessor") MessageSourceAccessor messageSourceAccessor,
                                                @Qualifier("userService") UserService userService) {
        CustomFormAuthenticationFilter authenticationFilter = new CustomFormAuthenticationFilter(messageSourceAccessor,userService);
        authenticationFilter.setLoginUrl("/login");
        return authenticationFilter;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("permissionService") PermissionService permissionService) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager(permissionService));
        return authorizationAttributeSourceAdvisor;
    }

    public LogoutFilter logoutFilter() {
        LogoutFilter logout = new LogoutFilter();
        logout.setRedirectUrl("/toLogin");
        return logout;
    }
    @Bean
    public KaptchaValidateFilter kaptchaValidate() {
        return new KaptchaValidateFilter();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("messageSourceAccessor") MessageSourceAccessor messageSourceAccessor,
                                              @Qualifier("userService") UserService userService,
                                              @Qualifier("permissionService") PermissionService permissionService) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager(permissionService));
        bean.setLoginUrl("/toLogin");
        bean.setUnauthorizedUrl("/unauthor");
        bean.setSuccessUrl("/index");

        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("kaptchaValidate", kaptchaValidate());
        filters.put("authc", authcFilter(messageSourceAccessor,userService));
        filters.put("logout", logoutFilter());
        bean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = Maps.newHashMap();
        filterChainDefinitionMap.put("/toLogin", "anon");
        filterChainDefinitionMap.put("/login", "kaptchaValidate,authc");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/demo/**", "anon");
        filterChainDefinitionMap.put("/kaptcha.jpg", "anon");
        filterChainDefinitionMap.put("/app/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/actuator/**", "anon");
        filterChainDefinitionMap.put("/**", "user");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(@Qualifier("messageSource") MessageSource messageSource){
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
	public ShiroDialect shiroDialect(){
    	return new ShiroDialect();
	}
}
