package com.hulk.limitb.strategy;


import com.google.common.collect.Lists;
import com.hulk.limitb.constant.LimitbInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Instant;
import java.util.List;

/**
 * @author hulk
 * redis限流
 */
@Slf4j
@RequiredArgsConstructor
public class RedisLimitbStrategy extends BaseLimitbStrategy {


    private final RedisScript<List<Long>> redisRequestRateLimiterScript;

    private final StringRedisTemplate limitStringRedisTemplate;

    @Override
    public boolean execute(LimitbInfo info) {

        int replenish = info.getReplenish();
        int capacity = info.getCapacity();
        String limitName = info.getLimitName();
        Response results = isAllowed(limitName, replenish, capacity);
        log.info("限流KEY:[{}]拦截中,还剩:[{}]个TOKEN,每秒生成:[{}]个TOKEN,最多:[{}]个TOKEN", limitName,replenish, capacity, results.tokensRemaining);
        if (results.allowed) {
            return true;
        }
        return false;
    }

    private Response isAllowed(String id, int replenishRate, int burstCapacity) {
        return this.isAllowed(id, replenishRate, burstCapacity, 1);
    }

    private Response isAllowed(String id, int replenishRate, int burstCapacity, int requestedTokens) {
        try {

            List<String> keys = getKeys(id);
            // The arguments to the LUA script. time() returns unixtime in seconds.
            // allowed, tokens_left = redis.eval(SCRIPT, keys, args)
            List<Long> results = this.limitStringRedisTemplate.execute(redisRequestRateLimiterScript, keys, replenishRate + "", burstCapacity + "", Instant.now().getEpochSecond() + "",
                    requestedTokens + "");
            // .log("redisratelimiter", Level.FINER);
            boolean allowed = (results.get(0) == 1L);
            Long tokensLeft = results.get(1);
            Response response = new Response(allowed, tokensLeft);
            return response;
        } catch (Exception e) {
            /*
             * We don't want a hard dependency on Redis to allow traffic. Make sure to set
             * an alert so you know if this is happening too much. Stripe's observed
             * failure rate is 0.01%.
             */
            log.error("Error determining if user allowed from redis", e);
        }
        return new Response(true, -1L);
    }

    private static List<String> getKeys(String id) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster
        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + id;
        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Lists.newArrayList(tokenKey, timestampKey);
    }

    @Data
    class Response {

        private final boolean allowed;
        private final long tokensRemaining;

        public Response(boolean allowed, long tokensRemaining) {
            this.allowed = allowed;
            this.tokensRemaining = tokensRemaining;
        }

    }
}
