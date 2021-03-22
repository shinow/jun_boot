package com.hulk.Idempotent.config;


import com.hulk.Idempotent.enums.IdempotentType;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * tenant properties
 *
 * @author hulk
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "spring.idempotent")
public class IdempotentProperties {


    private IdempotentType type = IdempotentType.IDEMPOTENT_HEAD;
    //单位秒
    private long timeout = 60*60;

    private String module = "module";



}
