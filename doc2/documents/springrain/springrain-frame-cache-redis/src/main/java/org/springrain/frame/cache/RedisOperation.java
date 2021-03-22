package org.springrain.frame.cache;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.GlobalStatic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redisson的 redis操作,包含lock和queue
 *
 * @author caomei
 */

//@Component("redisOperation")
public class RedisOperation {
    private Logger logger = LoggerFactory.getLogger(getClass());
    // @Resource
    private RedissonClient redissonClient;

    //远程Service默认的工作并发
    private int remoteServiceWorkersAmount = 1000;

    private int queueCapacity = 1000;

    //是否接收队列,默认是true,如果是false就不再接受消息,用于tomcat重启之前,保证tomcat不再接受队列
    private boolean receiveQueue = true;


    /**
     * 根据Key 和超时时间加锁
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean lock(String key, long expire) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        try {
            RLock rLock = redissonClient.getLock(GlobalStatic.projectKeyPrefix + key + "_lock");
            //不做任何等待,抢不到就返回false
            if (rLock.tryLock(-1, expire, TimeUnit.MILLISECONDS)) {
                return true;
            }
            return false;
        } catch (InterruptedException e) {
            logger.error("locking error", e);
            return false;
        }


    }

    /**
     * 根据Key解锁
     *
     * @param key
     */
    public void unlock(String key) {

        if (StringUtils.isBlank(key)) {
            return;
        }
        try {
            RLock rLock = redissonClient.getLock(GlobalStatic.projectKeyPrefix + key + "_lock");
            if (rLock.isLocked()) {
                rLock.unlock();
            }
        } catch (Throwable e) {
            logger.error("unlock error", e);
        }
    }


    public <T> BlockingQueue<T> getBlockingQueue(String queueName, Class<T> clazz) {
        RBlockingQueue<T> queue = redissonClient.getBlockingQueue(GlobalStatic.projectKeyPrefix + queueName);
        return queue;
    }

    public RAtomicLong getAtomicLong(String name) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(GlobalStatic.projectKeyPrefix + name);
        return atomicLong;

    }

    public RAtomicLong getAtomicLong(String name, Long initValue) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(GlobalStatic.projectKeyPrefix + name);
        atomicLong.set(initValue);
        return atomicLong;

    }

    /**
     * 注册到远程Service服务(基于redisson实现的RPC)
     *
     * @param clazz
     * @param t
     */
    public <T> void registerRemoteService(Class<T> clazz, T t) {

        RRemoteService remoteService = getRedissonClient().getRemoteService();
        // 注册了1000个服务端工作者实例，可以同时执行1000个并发调用
        remoteService.register(clazz, t, remoteServiceWorkersAmount);

    }

    /**
     * 获取远程的Service(基于redisson实现的RPC)
     *
     * @param clazz
     * @return
     */


    public <T> T getRemoteService(Class<T> clazz) {
        RRemoteService remoteService = getRedissonClient().getRemoteService();
        T t = remoteService.get(clazz);
        return t;

    }

    public boolean getReceiveQueue() {
        return receiveQueue;
    }

    public void setReceiveQueue(boolean receiveQueue) {
        this.receiveQueue = receiveQueue;
    }


    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


}
