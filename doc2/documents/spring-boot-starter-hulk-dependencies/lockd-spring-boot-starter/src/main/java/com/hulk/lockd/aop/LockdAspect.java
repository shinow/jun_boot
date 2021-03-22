package com.hulk.lockd.aop;

import com.hulk.lockd.annotation.Lockd;
import com.hulk.lockd.exception.LockdInvocationException;
import com.hulk.lockd.expression.LockInfoResolver;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import com.hulk.lockd.lock.Lock;
import com.hulk.lockd.lock.LockFactory;
import com.hulk.lockd.constant.LockInfo;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author  hulk
 * 给添加@Lockd切面加锁处理
 */
@Slf4j
@Aspect
public class LockdAspect {

    public LockdAspect(LockFactory lockFactory, LockInfoResolver lockInfoResolver){
       this.lockFactory =lockFactory;
        this.lockInfoResolver = lockInfoResolver;
    }


    private  LockFactory lockFactory;


    private LockInfoResolver lockInfoResolver;

    private final Map<String, LockResp> currentThreadLock = new ConcurrentHashMap<>();


    @Around(value = "@annotation(lockd)")
    public Object around(ProceedingJoinPoint joinPoint, Lockd lockd) throws Throwable {
        LockInfo lockInfo = lockInfoResolver.get(joinPoint,lockd);
        String curentLock = this.getCurrentLockId(joinPoint,lockd);
        currentThreadLock.put(curentLock,new LockResp(lockInfo, false));
        Lock lock = lockFactory.getLock(lockInfo);
        boolean lockResp = lock.acquire();

        //如果获取锁失败了，则进入失败的处理逻辑
        if(!lockResp) {
                log.warn("Timeout while acquiring Lock({})", lockInfo.getName());
            //如果自定义了获取锁失败的处理策略，则执行自定义的降级处理策略
            if(!StringUtils.isEmpty(lockd.customLockTimeoutStrategy())) {
                return handleCustomLockTimeout(lockd.customLockTimeoutStrategy(), joinPoint);
            } else {
                //否则执行预定义的执行策略
                //注意：如果没有指定预定义的策略，默认的策略为静默啥不做处理
                lockd.lockTimeoutStrategy().handle(lockInfo, lock, joinPoint);
            }
        }

        currentThreadLock.get(curentLock).setLock(lock);
        currentThreadLock.get(curentLock).setRb(true);

        return joinPoint.proceed();
    }

    @AfterReturning(value = "@annotation(lockd)")
    public void afterReturning(JoinPoint joinPoint, Lockd lockd) throws Throwable {
        String curentLock = this.getCurrentLockId(joinPoint,lockd);
        releaseLock(lockd, joinPoint,curentLock);
        cleanUpThreadLocal(curentLock);
    }

    @AfterThrowing(value = "@annotation(lockd)", throwing = "ex")
    public void afterThrowing (JoinPoint joinPoint, Lockd lockd, Throwable ex) throws Throwable {
        String curentLock = this.getCurrentLockId(joinPoint,lockd);
        releaseLock(lockd, joinPoint,curentLock);
        cleanUpThreadLocal(curentLock);
        throw ex;
    }

    /**
     * 处理自定义加锁超时
     */
    private Object handleCustomLockTimeout(String lockTimeoutHandler, JoinPoint joinPoint) throws Throwable {

        // prepare invocation context
        Method currentMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Object target = joinPoint.getTarget();
        Method handleMethod ;
        try {
            handleMethod = joinPoint.getTarget().getClass().getDeclaredMethod(lockTimeoutHandler, currentMethod.getParameterTypes());
            handleMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Illegal annotation param customLockTimeoutStrategy",e);
        }
        Object[] args = joinPoint.getArgs();

        // invoke
        Object resp ;
        try {
            resp = handleMethod.invoke(target, args);
        } catch (IllegalAccessException e) {
            throw new LockdInvocationException("Fail to invoke custom lock timeout handler: " + lockTimeoutHandler ,e);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }

        return resp;
    }

    /**
     *  释放锁
     */
    private void releaseLock(Lockd lockd, JoinPoint joinPoint,String curentLock) throws Throwable {
        LockResp lockResp = currentThreadLock.get(curentLock);
        if (lockResp.getRb()) {
            boolean releaseRes = currentThreadLock.get(curentLock).getLock().release();
            // avoid release lock twice when exception happens below
            lockResp.setRb(false);
            if (!releaseRes) {
                handleReleaseTimeout(lockd, lockResp.getLockInfo(), joinPoint);
            }
        }
    }

    // avoid memory leak
    private void cleanUpThreadLocal(String curentLock) {
        currentThreadLock.remove(curentLock);
    }

    /**
     * 获取当前锁在map中的key
     * @param joinPoint
     * @param lockd
     * @return
     */
    private String getCurrentLockId(JoinPoint joinPoint , Lockd lockd){
        LockInfo lockInfo = lockInfoResolver.get(joinPoint,lockd);
        String curentLock= Thread.currentThread().getId() + lockInfo.getName();
        return curentLock;
    }

    /**
     *  处理释放锁时已超时
     */
    private void handleReleaseTimeout(Lockd lockd, LockInfo lockInfo, JoinPoint joinPoint) throws Throwable {

        if(log.isWarnEnabled()) {
            log.warn("Timeout while release Lock({})", lockInfo.getName());
        }

        if(!StringUtils.isEmpty(lockd.customReleaseTimeoutStrategy())) {

            handleCustomReleaseTimeout(lockd.customReleaseTimeoutStrategy(), joinPoint);

        } else {
            lockd.releaseTimeoutStrategy().handle(lockInfo);
        }

    }

    /**
     * 处理自定义释放锁时已超时
     */
    private void handleCustomReleaseTimeout(String releaseTimeoutHandler, JoinPoint joinPoint) throws Throwable {

        Method currentMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Object target = joinPoint.getTarget();
        Method handleMethod = null;
        try {
            handleMethod = joinPoint.getTarget().getClass().getDeclaredMethod(releaseTimeoutHandler, currentMethod.getParameterTypes());
            handleMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Illegal annotation param customReleaseTimeoutStrategy",e);
        }
        Object[] args = joinPoint.getArgs();

        try {
            handleMethod.invoke(target, args);
        } catch (IllegalAccessException e) {
            throw new LockdInvocationException("Fail to invoke custom release timeout handler: " + releaseTimeoutHandler, e);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    private class LockResp {

        private LockInfo lockInfo;
        private Lock lock;
        private Boolean rb;

        LockResp(LockInfo lockInfo, Boolean rb) {
            this.lockInfo = lockInfo;
            this.rb = rb;
        }

        LockInfo getLockInfo() {
            return lockInfo;
        }

        public Lock getLock() {
            return lock;
        }

        public void setLock(Lock lock) {
            this.lock = lock;
        }

        Boolean getRb() {
            return rb;
        }

        void setRb(Boolean rb) {
            this.rb = rb;
        }

        void setLockInfo(LockInfo lockInfo) {
            this.lockInfo = lockInfo;
        }
    }
}
