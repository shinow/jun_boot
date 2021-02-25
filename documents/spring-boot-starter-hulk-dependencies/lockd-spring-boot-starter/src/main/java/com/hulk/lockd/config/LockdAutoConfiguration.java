package com.hulk.lockd.config;

import com.hulk.lockd.annotation.EnableLockdConfig;
import com.hulk.lockd.aop.LockdAspect;
import com.hulk.lockd.exception.LockdInvocationException;
import com.hulk.lockd.expression.LockdKeyExpressionResolver;
import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import com.hulk.lockd.expression.LockInfoResolver;
import com.hulk.lockd.lock.LockFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * @author hulk
 * lockd自动装配
 */
@Configuration
@ConditionalOnBean(annotation = EnableLockdConfig.class)
@ConditionalOnClass(RedissonClient.class)
@EnableConfigurationProperties(LockdProperties.class)
public class LockdAutoConfiguration {


    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    RedissonClient redisson(LockdProperties lockdProperties) throws Exception {
        Config config = new Config();
        LockdProperties.ClusterServer  clusterServer = lockdProperties.getClusterServer();
        if (clusterServer != null) {
            String[] addresses = clusterServer.getNodeAddresses();
            if (addresses.length == 0) {
                new LockdInvocationException("ClusterServer 配置错误。");
            }
            String[]  nodeAddresses = {};
            for (int i = 0; i < addresses.length; i++) {
                nodeAddresses[i] = "redis://" + addresses[i] ;
            }
            config.useClusterServers().setPassword(lockdProperties.getPassword())
                    .addNodeAddress(nodeAddresses).setTimeout(lockdProperties.getTimeout());
        } else {
            config.useSingleServer().setAddress("redis://" + lockdProperties.getHost() + ":" + lockdProperties.getPort())
                    .setDatabase(lockdProperties.getDatabase())
                    .setPassword(lockdProperties.getPassword()).setTimeout(lockdProperties.getTimeout());
        }
        Codec codec = (Codec) ClassUtils.forName(lockdProperties.getCodec(), ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(codec);
        config.setEventLoopGroup(new NioEventLoopGroup());
        return Redisson.create(config);
    }

    @Bean
    public LockdKeyExpressionResolver lockdKeyExpressionResolver() {
        return new LockdKeyExpressionResolver();
    }

    @Bean
    public LockInfoResolver lockInfoResolver(LockdKeyExpressionResolver lockdKeyExpressionResolver,LockdProperties lockdProperties) {
        return new LockInfoResolver(lockdKeyExpressionResolver,lockdProperties);
    }



    @Bean
    public LockFactory lockFactory( RedissonClient redisson) {
        return new LockFactory(redisson);
    }

    @Bean
    public LockdAspect lockdAspect(LockFactory lockFactory, LockInfoResolver lockInfoResolver) {
        return new LockdAspect(lockFactory, lockInfoResolver);
    }

}
