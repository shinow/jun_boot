package com.hulk.limitb.expression;


import com.hulk.limitb.annotation.Limitb;
import com.hulk.limitb.config.LimitbProperties;
import com.hulk.limitb.constant.LimitbInfo;
import com.hulk.limitb.enums.LimitbStrategy;
import com.hulk.limitb.enums.LimitbType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hulk
 */
@Slf4j
public class LimitbInfoResolver  {


    private static final String LIMIT_NAME_PREFIX = "LOMITB";
    private static final String LIMIT_NAME_SEPARATOR = "$";
    private static final String UNKNOWN = "unknown";
    private LimitbKeyExpressionResolver limitbKeyExpressionResolver;

    public LimitbInfoResolver(LimitbKeyExpressionResolver limitbKeyExpressionResolver) {
        this.limitbKeyExpressionResolver =limitbKeyExpressionResolver;
    }

    public LimitbInfo get(JoinPoint joinPoint, Limitb limitb, LimitbProperties p) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        int replenish = limitb.replenish();
        int capacity = limitb.capacity();
        LimitbType limitbType = limitb.limitType();
        LimitbStrategy limitbStrategy = limitb.limitStrategy();
        if (replenish <= 0) {
            replenish = p.getReplenish();
        }
        if (capacity <= 0) {
            capacity = p.getCapacity();
        }
        if (capacity < replenish) {
            capacity = replenish;
        }
        if (limitbType == LimitbType.NOOP) {
            limitbType = p.getLimitType();
        }
        if (limitbStrategy == LimitbStrategy.NOOP) {
            limitbStrategy = p.getLimitStrategy();
        }
        String keyName ="";
        if (limitbType == LimitbType.IP) {
            keyName = getIP();
        }else {
            keyName = limitbKeyExpressionResolver.getKeyName(joinPoint, limitb);
        }


        String limitName = LIMIT_NAME_PREFIX + LIMIT_NAME_SEPARATOR + getName(signature) + (StringUtils.isEmpty(keyName)?"":LIMIT_NAME_SEPARATOR+keyName);
        log.debug("限流标识limitName:[{}]", limitName);
        return new LimitbInfo(limitName, replenish, capacity, limitbType, limitbStrategy);
    }

    /**
     * 获取锁的name，按全类名拼接方法名处理
     *
     * @param signature
     * @return
     */
    private String getName( MethodSignature signature) {
       return String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
    }

    /**
     * 获取ip
     *
     * @return {String}
     */
    private  String getIP() {
        HttpServletRequest request =  getRequest();
        Assert.notNull(request, "HttpServletRequest is null");
        String ip = request.getHeader("X-Requested-For");
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return !StringUtils.hasText(ip) ? null : ip.split(",")[0];
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return {HttpServletRequest}
     */
    private  HttpServletRequest getRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
