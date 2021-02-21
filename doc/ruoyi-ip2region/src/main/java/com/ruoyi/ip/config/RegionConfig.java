package com.ruoyi.ip.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "region")
public class RegionConfig
{
    private String  dbpath;

    private Integer algorithm;

    private String  qqpath;
}
