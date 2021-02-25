package com.hulk.limitb.annotation;



import com.hulk.limitb.enums.LimitbStrategy;
import com.hulk.limitb.enums.LimitbType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author hulk
 * @date 2018/9/10 15:52
 */

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface Limitb {


    String [] keys() default {};

    //每秒生产令牌数量
    int replenish() default 0;

    //令牌桶的容量，允许在一秒钟内完成的最大请求数
    int capacity() default 0;

    LimitbType limitType() default LimitbType.NOOP;

    LimitbStrategy limitStrategy() default LimitbStrategy.NOOP;

}