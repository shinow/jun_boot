package com.ruoyi.project.wxapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 应用配置
 */

@Configuration
@PropertySource(value = "", ignoreResourceNotFound = true,encoding = "UTF-8" )
@ConfigurationProperties(prefix = "app")
@Data
public class AppPropConfig {
    private String domain;
}
