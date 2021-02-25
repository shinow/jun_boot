package com.hulk.limitb.config;


import com.hulk.limitb.aop.LimitbAspect;
import com.hulk.limitb.strategy.RedisLimitbStrategy;
import com.hulk.limitb.strategy.SimpleLimitbStrategy;
import com.hulk.limitb.expression.LimitbInfoResolver;
import com.hulk.limitb.expression.LimitbKeyExpressionResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * configuration
 *
 * @author hulk
 */
@Configuration
@ConditionalOnBean(RedisConnectionFactory.class)
@ConditionalOnProperty(value = "spring.limitb.enabled", havingValue = "true")
@EnableConfigurationProperties(LimitbProperties.class)
public class LimitbAutoConfiguration {

    @Bean
    public LimitbKeyExpressionResolver limitbKeyExpressionResolver() {
        LimitbKeyExpressionResolver provider = new LimitbKeyExpressionResolver();
        return provider;
    }

    @Bean
    public LimitbInfoResolver limitbInfoResolver( LimitbKeyExpressionResolver limitbKeyExpressionResolver) {
        LimitbInfoResolver provider = new LimitbInfoResolver(limitbKeyExpressionResolver);
        return provider;
    }

    @Bean
    public LimitbAspect limitbAspect(LimitbInfoResolver limitbInfoResolver,LimitbProperties  properties) {
        LimitbAspect interceptor = new LimitbAspect(limitbInfoResolver,properties);
        return interceptor;
    }



    @Bean
    @SuppressWarnings("unchecked")
    public RedisScript redisRequestRateLimiterScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("META-INF/scripts/request_rate_limiter.lua")));
        redisScript.setResultType(List.class);
        return redisScript;
    }

    @Bean
    @ConditionalOnMissingBean(name = "limitStringRedisTemplate")
    public StringRedisTemplate limitStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    @Bean
    public RedisLimitbStrategy redisLimitStrategy(RedisScript redisRequestRateLimiterScript, StringRedisTemplate limitStringRedisTemplate) {
        return new RedisLimitbStrategy(redisRequestRateLimiterScript, limitStringRedisTemplate);
    }

    @Bean
    public SimpleLimitbStrategy simpleLimitStrategy() {
        return new SimpleLimitbStrategy();
    }

}
