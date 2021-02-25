package com.hulk.Idempotent.util;

import com.hulk.Idempotent.constant.GlobalConstant;
import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisIdempotentUtils {

     //超时时间
     @Setter
     private long timeout = 60*60+1 ;
     @Setter
     private String  module = "module1";
     @Setter
     private  RedisTemplate redisTemplate;

    // 将idempotent存入在redis
    public String getIdempotent() {
        String idempotent = GlobalConstant.IDEMPOTENT.concat(":").concat(UUID.randomUUID().toString());
        String  key = module.concat(":").concat(idempotent);
        String value = key;
        //key: idempotent value: idempotent 时间
        this.setString(key, value, timeout);
        return value;
    }


    //从redis查询对应的idempotent   防止没来得及删除 只有一个线程操作 其实redis已经可以防止了
    public synchronized boolean findIdempotent(String idempotentKey) {
        String idempotent = (String) this.getString(idempotentKey);
        //要么被被人使用过了 要么没有对应idempotent
        if (StringUtils.isEmpty(idempotent)) {
            return false;
        }
        // idempotent 获取成功后 删除对应idempotentMapsidempotent
        this.delKey(idempotent);
        //保证每个接口对应的idempotent只能访问一次，保证接口幂等性问题
        return true;
    }

    private void setString(String key, Object data, Long timeout) {

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        if (data instanceof String) {
            String value = (String) data;
            redisTemplate.opsForValue().set(key, value);
        }

        if (timeout != null) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    private Object getString(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate.opsForValue().get(key);
    }

    private void delKey(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.delete(key);
    }

}