package com.pearadmin.pro.common.secure.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Describe: 缓存公共实体
 * Author: 就 眠 仪 式
 *  * CreateTime: 2019/10/23
 * */
@Data
public class SecureCache implements Serializable {

    /**
     * Token 过期时间
     * */
    private LocalDateTime expiredTime;
}
