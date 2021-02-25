package org.springrain.frame.cache;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;

/**
 * redisson的SpringCache 用于扩展实现Cache超时, 加上超时,吞吐量下降非常厉害,原因待查,暂时废弃
 *
 * @author caomei
 */
@Deprecated
public class FrameRedissonSpringCacheManager extends RedissonSpringCacheManager {

    //全局的超时时间
    private long cacheTimeOut = 0L;


    public FrameRedissonSpringCacheManager(RedissonClient redisson, long cacheTimeOut) {
        super(redisson);
        this.cacheTimeOut = cacheTimeOut;
    }

    /**
     * 用于设置全局的超时时间,复写父类的createDefaultConfig方法
     *
     * @return CacheConfig
     */
    @Override
    protected CacheConfig createDefaultConfig() {

        CacheConfig config = new CacheConfig();
        config.setMaxIdleTime(cacheTimeOut);

        return config;

    }


}
