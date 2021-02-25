package com.hulk.sequence.properties;

/**
 * @author hulk
 * @date 2019-05-26
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hulk
 * @date 2019/5/26
 * <p>
 * 发号器Redis配置属性
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "xsequence.redis")
public class SequenceRedisProperties extends BaseSequenceProperties {

}