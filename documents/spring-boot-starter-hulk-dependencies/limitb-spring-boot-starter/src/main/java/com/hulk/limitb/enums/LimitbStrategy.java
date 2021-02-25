package com.hulk.limitb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author hulk
 * 限流策略工具
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LimitbStrategy {
    /**
     * 本地guava
     */
    LOCAL("SimpleLimitbStrategy","com.hulk.limitb.strategy.SimpleLimitbStrategy"),
    /**
     * redis
     */
    REDIS("RedisLimitbStrategy","com.hulk.limitb.strategy.RedisLimitbStrategy"),

    NOOP("","");

    private String code;
    private String clazz;


}
