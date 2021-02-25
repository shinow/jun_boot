package com.hulk.lockd.annotation;

import java.lang.annotation.*;

/**
 * @author hulk
 *
 */
@Target(value = {ElementType.PARAMETER, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface LockdKey {
    String value() default "";
}
