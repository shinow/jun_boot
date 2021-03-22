package com.hulk.multimail.config;

import com.hulk.multimail.annotation.EnableMultiMailConfig;
import com.hulk.multimail.service.MultiMailSendTemplate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author: hulk
 * @date: 2020/7/14
 * 实现多账号，邮箱轮询发送
 */
@Configuration
@ConditionalOnBean(annotation = EnableMultiMailConfig.class)
@EnableConfigurationProperties({MultiMailProperties.class})
public class MultiMailAutoConfiguration {


    @Bean
    @Lazy
    @ConditionalOnMissingBean(MultiMailSendTemplate.class)
    public MultiMailSendTemplate multiMailSendTemplate(MultiMailProperties multiMailProperties) {
        return new MultiMailSendTemplate(multiMailProperties);
    }




}