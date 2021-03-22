package com.hulk.Idempotent.annotation;



import java.lang.annotation.*;

/**
 * 
 * @author hulk
 * @version 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableIdempotentConfig {

    //String[] basePackages();

}
