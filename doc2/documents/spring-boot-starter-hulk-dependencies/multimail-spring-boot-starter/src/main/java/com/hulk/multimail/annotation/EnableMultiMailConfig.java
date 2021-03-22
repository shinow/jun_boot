package com.hulk.multimail.annotation;



import java.lang.annotation.*;

/**
 * 
 * @author hulk
 * @version 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableMultiMailConfig {

    //String[] basePackages();

}
