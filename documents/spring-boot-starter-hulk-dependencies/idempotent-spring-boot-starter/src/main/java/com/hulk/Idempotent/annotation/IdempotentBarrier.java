package com.hulk.Idempotent.annotation;

import com.hulk.Idempotent.enums.IdempotentType;
import org.springframework.http.MediaType;


import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdempotentBarrier {

    IdempotentType value();

    String  mediaType() default MediaType.APPLICATION_JSON_UTF8_VALUE;

    String info() default "请勿重复提交!";
}