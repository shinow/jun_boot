package org.springrain.frame.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存的配置,自定义 cacheManager 用于实现替换.
 *
 * @author caomei
 */
@Configuration("configuration-MemoryCacheConfig")
public class MemoryCacheConfig {

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        CacheManager cacheManager = new ConcurrentMapCacheManager();

        return cacheManager;
    }

}
