package com.hulk.sequence.config;


import com.hulk.sequence.builder.DbSeqBuilder;
import com.hulk.sequence.builder.RedisSeqBuilder;
import com.hulk.sequence.builder.SnowflakeSeqBuilder;
import com.hulk.sequence.properties.SequenceDbProperties;
import com.hulk.sequence.properties.SequenceRedisProperties;
import com.hulk.sequence.properties.SequenceSnowflakeProperties;
import com.hulk.sequence.range.impl.name.DateBizName;
import com.hulk.sequence.range.impl.name.DefaultBizName;
import com.hulk.sequence.sequence.Sequence;
import com.hulk.sequence.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;
import java.time.Instant;


/**
 * @author hulk
 * @date 2019-05-26
 */
@Configuration
@ConditionalOnProperty(name = "xsequence.enable", havingValue = "true")
public class SequenceAutoConfiguration {




	/**
	 * 数据库作为发号器的存储介质
	 */
	@Configuration
	@ConditionalOnBean({DataSource.class})
	@ConditionalOnProperty(name = "xsequence.name", havingValue = "db")
	@EnableConfigurationProperties({SequenceDbProperties.class})
	public static class DbSequenceConfig {

		@Autowired(required = false)
		private DataSource dataSource;

		@Bean
		@ConditionalOnMissingBean
		public Sequence dbSequence(SequenceDbProperties  sequenceDbProperties) {
			return DbSeqBuilder
					.create()
					.bizName(new DefaultBizName(sequenceDbProperties.getBizName()))
					.dataSource(dataSource)
					.step(sequenceDbProperties.getStep())
					.stepStart(sequenceDbProperties.getStepStart())
					.retryTimes(sequenceDbProperties.getRetryTimes())
					.tableName(sequenceDbProperties.getTableName())
					.build();
		}
	}


	/**
	 * Redis 作为发号器的存储介质
	 */
	@Configuration
	@ConditionalOnBean({RedisConnectionFactory.class})
	@ConditionalOnProperty(name = "xsequence.name", havingValue = "redis")
	@EnableConfigurationProperties({SequenceRedisProperties.class})
	public static class RedisSequenceConfig {


		@Bean(value = "seqRedisTemplate")
		public RedisTemplate<String, Object> seqRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
			RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.setHashKeySerializer(new StringRedisSerializer());
			redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
			redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
			redisTemplate.setConnectionFactory(redisConnectionFactory);
			return redisTemplate;
		}

//		@Bean
//		@ConditionalOnMissingBean
//		public Sequence redisSequence() {
//			JedisPoolConfig  config = new JedisPoolConfig();
//			config.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
//			config.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
//			config.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
//			config.setMinIdle(redisProperties.getJedis().getPool().getMaxIdle());
//			return RedisSeqBuilder
//					.create()
//					.bizName(new DateBizName(sequenceRedisProperties.getBizName()))
//					.ip(redisProperties.getHost())
//					.port(redisProperties.getPort())
//					.auth(redisProperties.getPassword())
//					.step(sequenceRedisProperties.getStep())
//					.stepStart(System.currentTimeMillis())
//					.dataBase(redisProperties.getDatabase())
//					.timeOut((int)redisProperties.getTimeout().toMillis())
//					.setConfig(config)
//					.build();
//		}

		@Bean
		@ConditionalOnMissingBean
		public Sequence redisSequence(RedisTemplate seqRedisTemplate,SequenceRedisProperties  sequenceRedisProperties) {

			return RedisSeqBuilder
					.create()
					.bizName(new DateBizName(sequenceRedisProperties.getBizName()))
					.step(sequenceRedisProperties.getStep())
					.stepStart(sequenceRedisProperties.getStepStart())
					.seqRedisTemplate(seqRedisTemplate)
					.build();
		}
	}



	/**
	 * snowflak 算法作为发号器实现
	 */
	@Configuration
	@ConditionalOnProperty(name = "xsequence.name", havingValue = "snowflake", matchIfMissing = true)
	@EnableConfigurationProperties({SequenceSnowflakeProperties.class})
	public static class SnowflakeSequence {

		@Bean
		@ConditionalOnMissingBean
		public Sequence snowflakeSequence(SequenceSnowflakeProperties  sequenceSnowflakeProperties) {
			return SnowflakeSeqBuilder
					.create()
					.datacenterId(sequenceSnowflakeProperties.getDatacenterId())
					.build();
		}
	}


}