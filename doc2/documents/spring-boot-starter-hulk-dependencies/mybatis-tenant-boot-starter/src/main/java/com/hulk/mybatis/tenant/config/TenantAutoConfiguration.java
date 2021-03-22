package com.hulk.mybatis.tenant.config;



import com.hulk.mybatis.tenant.Interceptor.TenantInterceptor2;
import com.hulk.mybatis.tenant.annotation.EnableTenantConfig;


import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import com.hulk.mybatis.tenant.holder.TenantContextSessionCookieHolder;
import com.hulk.mybatis.tenant.holder.TenantContextThreadLocalHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * tenant configuration
 *
 * @author hulk
 */
@Configuration
@ConditionalOnBean(annotation = EnableTenantConfig.class)
@EnableConfigurationProperties(TenantProperties.class)
@ConditionalOnClass(TenantInterceptor2.class)
public class TenantAutoConfiguration {




  @Bean
  public TenantInterceptor2 tenantInterceptor (TenantProperties properties){

      TenantContextHolder tenantContextHolder ;
      if(properties.getTenantContextHolder()==null ) {
          tenantContextHolder = new TenantContextThreadLocalHolder();
      }else if (TenantContextHolder.class.isInstance(TenantContextThreadLocalHolder.class)){
          tenantContextHolder = new TenantContextThreadLocalHolder();
      }else if (TenantContextHolder.class.isInstance(TenantContextSessionCookieHolder.class)){
          tenantContextHolder = new TenantContextSessionCookieHolder();
      }else {
          tenantContextHolder = BeanUtils.instantiateClass(properties.getTenantContextHolder());
      }

    TenantInterceptor2 tenantInterceptor =  new TenantInterceptor2(properties.getTenantIdColumn(),  tenantContextHolder,properties.getIgnoreTenantTables(),properties.getMappedStatementIds());
    return  tenantInterceptor;
  }






}
