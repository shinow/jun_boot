package com.hulk.mybatis.tenant.annotation;

import java.lang.annotation.*;

/**
 * Enable tenant  for spring boot application
 * 
 * @author hulk
 * @version 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableTenantConfig {

}
