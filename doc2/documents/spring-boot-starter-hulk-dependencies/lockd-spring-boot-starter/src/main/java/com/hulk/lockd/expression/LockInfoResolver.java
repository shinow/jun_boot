package com.hulk.lockd.expression;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import com.hulk.lockd.annotation.Lockd;
import com.hulk.lockd.config.LockdProperties;
import com.hulk.lockd.constant.LockInfo;
import com.hulk.lockd.enums.LockType;
import org.springframework.util.StringUtils;

/**
 * @author  hulk
 */
@Slf4j
public class LockInfoResolver {

    private static final String LOCK_NAME_PREFIX = "LOCK";
    private static final String LOCK_NAME_SEPARATOR = "$";


    public LockInfoResolver(LockdKeyExpressionResolver lockdKeyExpressionResolver,LockdProperties lockdProperties){
        this.lockdKeyExpressionResolver =lockdKeyExpressionResolver;
        this.lockdProperties=lockdProperties;
    }

    private LockdProperties lockdProperties;

    private LockdKeyExpressionResolver lockdKeyExpressionResolver;


    public LockInfo get(JoinPoint joinPoint, Lockd lockd) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LockType type= lockd.lockType();
        String businessKeyName= lockdKeyExpressionResolver.getKeyName(joinPoint,lockd);
        //锁的名字，锁的粒度就是这里控制的
        String lockName = LOCK_NAME_PREFIX+LOCK_NAME_SEPARATOR+getName(lockd.name(), signature)+ (StringUtils.isEmpty(businessKeyName)?"":LOCK_NAME_SEPARATOR+businessKeyName);;
        //如果占用锁的时间设计不合理，则打印相应的警告提示
        long acquireTimeout = getAcquireTimeout(lockd);
        long expire = getExpireTime(lockd);

        if(expire == -1 && log.isWarnEnabled()) {
            log.warn("Trying to acquire Lock({}) with no expiration, " +
                        "lockd will keep prolong the lock expiration while the lock is still holding by current thread. " +
                        "This may cause dead lock in some circumstances.", lockName);
        }

        return new LockInfo(type,lockName,acquireTimeout,expire);
    }
    /**
     * 获取锁的name，如果没有指定，则按全类名拼接方法名处理
     * @param annotationName
     * @param signature
     * @return
     */

    private String getName(String annotationName, MethodSignature signature) {
        if (annotationName.isEmpty()) {
            return String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        } else {
            return annotationName;
        }
    }


    private long getAcquireTimeout(Lockd lock) {
        return lock.acquireTimeout() == Long.MIN_VALUE ?
                lockdProperties.getAcquireTimeout() : lock.acquireTimeout();
    }

    private long getExpireTime(Lockd lock) {
        return lock.expireTime() == Long.MIN_VALUE ?
                lockdProperties.getExpireTime() : lock.expireTime();
    }
}
