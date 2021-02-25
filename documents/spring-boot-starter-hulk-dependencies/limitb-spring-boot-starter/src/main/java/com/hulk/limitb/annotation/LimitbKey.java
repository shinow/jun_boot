package com.hulk.limitb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @author hulk
 * @date 2018/9/10 15:52
 * 限流参数key
 */

@Retention(RUNTIME)
@Target(value = {ElementType.PARAMETER, ElementType.TYPE})
@Documented
public @interface LimitbKey {

    String value() default "";

}