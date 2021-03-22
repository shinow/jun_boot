package com.hulk.Idempotent.annotation;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdempotentGenerator {

    //String value();
}