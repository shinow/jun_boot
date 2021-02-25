package com.hulk.limitb.config;

import com.hulk.limitb.enums.LimitbStrategy;
import com.hulk.limitb.enums.LimitbType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author  hulk
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = LimitbProperties.PREFIX)
public class LimitbProperties {

    public static final String PREFIX = "limitb";

    //每秒生产令牌数量
    int replenish = 2;

    //令牌桶的容量，允许在一秒钟内完成的最大请求数
    int capacity = 5 ;

    LimitbType limitType = LimitbType.CUSTOMER;

    LimitbStrategy limitStrategy = LimitbStrategy.LOCAL;







}
