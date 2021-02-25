package com.hulk.lockd.lock;

import com.hulk.lockd.constant.LockInfo;
import com.hulk.lockd.expression.LockInfoResolver;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author  hulk
 * Content :
 */
@Slf4j
public class LockFactory  {

    public LockFactory(RedissonClient redissonClient){
        this.redissonClient  = redissonClient;
    }

    private RedissonClient redissonClient;

    public Lock getLock(LockInfo lockInfo){
        switch (lockInfo.getType()) {

            case FAIR:
                return new FairLock(redissonClient, lockInfo);
            case READ:
                return new ReadLock(redissonClient, lockInfo);
            case WRITE:
                return new WriteLock(redissonClient, lockInfo);
            case REENTRANT:
                return new ReentrantLock(redissonClient, lockInfo);
            default:
                return new ReentrantLock(redissonClient, lockInfo);
        }
    }

}
