package com.hulk.limitb.strategy;


import com.hulk.limitb.cache.impl.TimedCache;
import com.google.common.util.concurrent.RateLimiter;


import com.hulk.limitb.constant.LimitbInfo;
import com.hulk.limitb.enums.LimitbStrategy;
import com.hulk.limitb.exception.LimitbException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


/**
 * @author hulk
 * 简单内存限流
 */
@Slf4j
public class SimpleLimitbStrategy extends BaseLimitbStrategy {

    private static final LimitbStrategy SIMPLE = LimitbStrategy.LOCAL;

    /** 创建缓存，默认60毫秒过期 */
    private static final  TimedCache<String, RateLimiter> LIMITEB_CACHE = new TimedCache<>(60);

    static {
        LIMITEB_CACHE.schedulePrune(55);
    }

    @SneakyThrows
    @Override
    public boolean execute(LimitbInfo info) {

        if (SIMPLE != info.getLimitStrategy()) {
            throw new LimitbException("限流策略非法，请使用本地限流。");
        }
        RateLimiter rateLimiter = LIMITEB_CACHE.get(info.getLimitName());
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(info.getReplenish());

            LIMITEB_CACHE.put(info.getLimitName(), rateLimiter,info.getReplenish()*1000);
        }
        if (!rateLimiter.tryAcquire()) {
            log.warn(SIMPLE.name() + "[{}]:限流中......",info.getLimitName());
            return false;
        }
        return true;
    }

    /**
     * 两个Double数相除，并保留scale位小数
     *
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    private static double div(long v1, long
            v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


}
