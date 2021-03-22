package com.hulk.lockd.annotation;

import com.hulk.lockd.enums.LockTimeoutStrategy;
import com.hulk.lockd.enums.LockType;
import com.hulk.lockd.enums.ReleaseTimeoutStrategy;

import java.lang.annotation.*;

/**
 * @author hulk
 * 加锁注解
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Lockd {
    /**
     * 锁的名称
     * 指定名称并且key参数和值相同的时候方便多个方法共用一个锁
     * @return name
     */
    String name() default "";
    /**
     * 自定义业务key
     * @return keys
     */
    String [] keys() default {};
    /**
     * 锁类型，默认可重入锁
     * @return lockType
     */
    LockType lockType() default LockType.REENTRANT;
    /**
     * 尝试获取锁超时时间 单位：毫秒
     * @return acquireTimeout
     */
    long acquireTimeout() default Long.MIN_VALUE;
    /**
     * 锁自动过期时间 单位：毫秒
     * @return expireTime
     */
    long expireTime() default Long.MIN_VALUE;

     /**
     * 加锁超时的处理策略
     * @return lockTimeoutStrategy
     */
     LockTimeoutStrategy lockTimeoutStrategy() default LockTimeoutStrategy.NO_OPERATION;

    /**
     * 自定义加锁超时的处理策略
     * @return customLockTimeoutStrategy
     */
     String customLockTimeoutStrategy() default "";

     /**
     * 释放锁时已超时的处理策略
     * @return releaseTimeoutStrategy
     */
     ReleaseTimeoutStrategy releaseTimeoutStrategy() default ReleaseTimeoutStrategy.NO_OPERATION;

    /**
     * 自定义释放锁时已超时的处理策略
     * @return customReleaseTimeoutStrategy
     */
     String customReleaseTimeoutStrategy() default "";

}
