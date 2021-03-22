package com.hulk.limitb.aop;


import com.hulk.limitb.config.LimitbProperties;
import com.hulk.limitb.annotation.Limitb;
import com.hulk.limitb.constant.LimitbInfo;
import com.hulk.limitb.exception.LimitbException;
import com.hulk.limitb.strategy.BaseLimitbStrategy;
import com.hulk.limitb.expression.LimitbInfoResolver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * @author hulk
 */
@Slf4j
@Aspect
public class LimitbAspect {

    public LimitbAspect(LimitbInfoResolver limitbInfoResolver,LimitbProperties limitbProperties){
        this.limitbInfoResolver =limitbInfoResolver;
        this.limitbProperties =limitbProperties;
    }
    private LimitbProperties limitbProperties;
    private LimitbInfoResolver limitbInfoResolver;
    @Autowired
    private Map<String, BaseLimitbStrategy> LimitStrategy;


    @Before(value = "@annotation(limitb)")
    @SneakyThrows
    public void joinPoint(JoinPoint joinPoint, Limitb limitb)  {
        LimitbInfo info = limitbInfoResolver.get(joinPoint,limitb,limitbProperties);
        boolean b = LimitStrategy.get(info.getLimitStrategy().getCode()).execute(info);

        if(!b){
            String msg = info.getLimitName() + ":请求频率过高，每秒内不能超过,"+ info.getReplenish() + "笔请求。最多，"+ info.getCapacity()+ "笔请求。";
            log.warn(msg);
            throw new LimitbException(msg);
        }
        return;
    }



}