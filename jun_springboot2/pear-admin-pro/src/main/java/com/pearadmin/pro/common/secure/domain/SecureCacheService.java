package com.pearadmin.pro.common.secure.domain;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Describe: 缓存公共服务
 * Author: 就 眠 仪 式
 *  * CreateTime: 2019/10/23
 * */
@Service
public class SecureCacheService<K,V> {

    /**
     * Redis 服务
     * */
    @Resource
    private RedisTemplate<K, V> redisTemplate;

    /**
     * 过期
     * */
    public long expired(K key){
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }

    /**
     * 内容
     * */
    public V get(K key){
       return redisTemplate.opsForValue().get(key);
    }

    /**
     * 扫描
     * */
    public Set<K> scanKey(K prefix){
        return redisTemplate.keys(prefix);
    }

    /**
     * 扫描
     * */
    public Set<V> scanValue(K prefix){
        Set<K> keys = scanKey(prefix);
        Set<V> values = new HashSet<>();
        keys.forEach(key -> {
            V value = get(key);
            values.add(value);
        });
        return values;
    }

    /**
     * 存储
     * */
    public void set(K key,V value,long time){
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 销毁
     * */
    public void destroy(K key){
        redisTemplate.delete(key);
    }

}
