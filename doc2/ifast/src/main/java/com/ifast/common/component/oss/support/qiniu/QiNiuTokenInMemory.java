package com.ifast.common.component.oss.support.qiniu;

import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * <small> 2019-06-04 14:30 | Aron</small>
 */
@Component
@Slf4j
public class QiNiuTokenInMemory {

    private Date lastGetTime;
    /**
     * 刷新周期，默认50分钟刷新一次
     * (官方默认token有效期是60分钟)
     */
    private long time = TimeUnit.MINUTES.toMillis(50);

    private String token;

    @Autowired
    private QiNiuOSSProperties config;

    /**
     * 获取token
     * @param isForceRefresh 是否强制刷新token
     * @return
     */
    public synchronized String get(boolean isForceRefresh){

        if(StringUtils.isBlank(token)){
            log.info("qiniu token init ...");
            String newToken = getNewToken();
            lastGetTime = new Date();
            token = newToken;
            return newToken;
        }

        Date now = new Date();
        long t = now.getTime() - lastGetTime.getTime();
        if(t <= time && !isForceRefresh){
            log.info("return old token:{}", token);
            return token;
        }

        String newToken = getNewToken();
        this.lastGetTime = now;
        this.token = newToken;
        log.info("return new token:{}", token);
        return newToken;

    }

    /**
     * 获取token
     * @return
     */
    public synchronized String get(){
        return get(false);

    }

    private String getNewToken() {
        String token = Auth.create(this.config.getAccessKey(), this.config.getSecretKey()).uploadToken(this.config.getBucket());
        log.info("qiniu refresh token:{}", token);
        return token;
    }

}
