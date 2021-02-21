package com.pearadmin.pro.common.aop;

import com.pearadmin.pro.common.aop.lang.annotation.Log;
import com.pearadmin.pro.common.aop.lang.enums.BusinessKind;
import com.pearadmin.pro.common.aop.lang.enums.BusinessStatus;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.modules.log.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * Describe: 日 志 切 面
 * Author: 就 眠 仪 式
 * CreateTime: 2021/2/3
 * */
@Aspect
@Component
public class LogAspect {

    /**
     * 日 志 服 务
     * */
    @Resource
    private LogService logService;

    /**
     * 切 面 编 程
     * */
    @Pointcut("@annotation(com.pearadmin.pro.common.aop.lang.annotation.Log)")
    public void pointcut(){ }

    /**
     * 行 为 日 志
     * */
    @Around("pointcut()")
    private Object around(ProceedingJoinPoint point) throws Throwable{
        Log annotation = getAnnotation(point);
        com.pearadmin.pro.modules.log.domain.Log log = new com.pearadmin.pro.modules.log.domain.Log();
        try {
            log.setTitle(annotation.title());
            log.setDescribe(annotation.describe());
            log.setBusiness(annotation.business());
            log.setMethod(ServletUtil.getMethod());
            log.setStatus(BusinessStatus.SUCCESS);
            log.setKind(BusinessKind.OPERATE);
            logService.save(log);
            return point.proceed();
        } catch (Exception e){
            log.setStatus(BusinessStatus.FAILURE);
        } finally {
            logService.save(log);
        }
        return null;
    }

    /**
     * 注 解 获 取
     * */
    private Log getAnnotation(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        Log annotation = targetClass.getAnnotation(Log.class);
        if(annotation != null){
            return annotation;
        } else {
            return signature.getMethod().getAnnotation(Log.class);
        }
    }
}