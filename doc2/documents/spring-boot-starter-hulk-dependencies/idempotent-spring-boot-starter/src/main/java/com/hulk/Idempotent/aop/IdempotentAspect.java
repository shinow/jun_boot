package com.hulk.Idempotent.aop;

import com.hulk.Idempotent.annotation.IdempotentBarrier;
import com.hulk.Idempotent.annotation.IdempotentGenerator;
import com.hulk.Idempotent.constant.GlobalConstant;
import com.hulk.Idempotent.enums.IdempotentType;
import com.hulk.Idempotent.exception.IdempotentException;
import com.hulk.Idempotent.util.RedisIdempotentUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@Aspect
@AllArgsConstructor
public class IdempotentAspect {


    private RedisIdempotentUtils redisIdempotentUtils;


    //需要作用的类
    @Pointcut(value = "@annotation(com.hulk.Idempotent.annotation.IdempotentGenerator)")
    public void beforeAop() {
    }

    //需要作用的类
    @Pointcut(value = "@annotation(com.hulk.Idempotent.annotation.IdempotentBarrier)")
    public void aroundAop() {
    }
    // 前置通知转发idempotent参数  进行拦截的逻辑
    @Before("beforeAop()")
    public void before(JoinPoint point) {
        //获取并判断类上是否有注解
        //统一的返回值
        MethodSignature signature = (MethodSignature) point.getSignature();
        IdempotentGenerator idempotentGenerator =  signature.getMethod().getDeclaredAnnotation(IdempotentGenerator.class);//参数是注解的那个
        //如果有注解的情况
        if (idempotentGenerator != null) {
            handleIdempotent();
        }
    }

    // 环绕通知验证参数
    @Around("aroundAop()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        IdempotentBarrier idempotentBarrier = signature.getMethod().getDeclaredAnnotation(IdempotentBarrier.class);
        //有注解的情况 有注解的说明需要进行Idempotent校验
        if (idempotentBarrier != null) {
            return handleIdempotent(proceedingJoinPoint, signature);
        }
        // 放行
        //放行 正常执行后面（Controller）的业务逻辑
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

    // 验证Idempotent  方法的封装
    public Object handleIdempotent(ProceedingJoinPoint proceedingJoinPoint, MethodSignature signature)
            throws Throwable {
        IdempotentBarrier idempotentBarrier = signature.getMethod().getDeclaredAnnotation(IdempotentBarrier.class);
        if (idempotentBarrier == null) {
            // 直接执行程序
            Object proceed = proceedingJoinPoint.proceed();
            return proceed;
        }
        // 代码步骤：
        // 1.获取令牌 存放在请求头中
        HttpServletRequest request = getRequest();
        // value就是获取类型 请求头之类的
        IdempotentType idempotentType = idempotentBarrier.value();
        String  mediaType = idempotentBarrier.mediaType();
        String errMsg = idempotentBarrier.info();
        if (StringUtils.isEmpty(idempotentType)) {
            throw new IdempotentException("参数错误!");
        }
        String idempotent ;
        //如果存在header中 从头中获取
        if (idempotentType.equals(IdempotentType.IDEMPOTENT_HEAD)) {
            //从头中获取
            idempotent = request.getHeader(GlobalConstant.IDEMPOTENT);
        } else {
            //否则从 请求参数获取
            idempotent = request.getParameter(GlobalConstant.IDEMPOTENT);
        }
        if (StringUtils.isEmpty(idempotent)) {
            throw new IdempotentException("参数错误!");
        }
        if (!redisIdempotentUtils.findIdempotent(idempotent)) {
            throw new IdempotentException(errMsg);
            //response(mediaType,errMsg);

        }
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

    public void handleIdempotent() {
        String idempotent = redisIdempotentUtils.getIdempotent();
        getRequest().setAttribute(GlobalConstant.IDEMPOTENT, idempotent);

    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    public void response( String mediaType , Object msg)  {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", mediaType);
        response.setContentType(mediaType);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(msg);
            writer.flush();
        } catch (Exception e) {
            log.error("",e);
        } finally {
            if(writer!=null){
                writer.close();
            }

        }

    }

}