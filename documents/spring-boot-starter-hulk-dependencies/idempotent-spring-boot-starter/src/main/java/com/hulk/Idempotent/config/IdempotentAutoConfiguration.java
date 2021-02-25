package com.hulk.Idempotent.config;


import com.hulk.Idempotent.annotation.EnableIdempotentConfig;
import com.hulk.Idempotent.aop.IdempotentAspect;
import com.hulk.Idempotent.util.RedisIdempotentUtils;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author hulk
 *
 * @see https://www.cnblogs.com/toov5/p/10312458.html
 */
@Configuration
@ConditionalOnBean(annotation = EnableIdempotentConfig.class,value = RedisConnectionFactory.class )
@EnableConfigurationProperties(IdempotentProperties.class)
public class IdempotentAutoConfiguration {



   @Bean(value = "idempotentRedisTemplate")
   public RedisTemplate<String, Object> idempotentRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
      RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setHashKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
      redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
      redisTemplate.setConnectionFactory(redisConnectionFactory);
      return redisTemplate;
   }
  @Bean
  @ConditionalOnMissingBean
  public RedisIdempotentUtils  redisIdempotentUtils(RedisTemplate<String, Object> idempotentRedisTemplate,IdempotentProperties properties){
      RedisIdempotentUtils  redisIdempotentUtils = new RedisIdempotentUtils();
      redisIdempotentUtils.setRedisTemplate(idempotentRedisTemplate);
      redisIdempotentUtils.setModule(properties.getModule());
      redisIdempotentUtils.setTimeout(properties.getTimeout());
     return redisIdempotentUtils;
  }

   @Bean
   @ConditionalOnMissingBean
   public IdempotentAspect idempotentAspect (RedisIdempotentUtils  redisIdempotentUtils){
      IdempotentAspect interceptor = new IdempotentAspect(redisIdempotentUtils);
      return  interceptor;
   }


}
