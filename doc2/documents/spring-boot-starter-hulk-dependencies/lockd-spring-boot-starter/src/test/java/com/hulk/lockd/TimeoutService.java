package com.hulk.lockd;

import com.hulk.lockd.annotation.Lockd;
import com.hulk.lockd.enums.LockTimeoutStrategy;
import com.hulk.lockd.enums.ReleaseTimeoutStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hulk
 * @since 2019/4/16
 **/
@Slf4j
@Service
public class TimeoutService {
    

    @Lockd(name="foo-service", expireTime =-1, releaseTimeoutStrategy = ReleaseTimeoutStrategy.FAIL_FAST)
    public void foo1() {
        try {
           log.info("foo1 acquire lock");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Lockd(name="foo-service", acquireTimeout=2, lockTimeoutStrategy = LockTimeoutStrategy.FAIL_FAST)
    public void foo2() {
        try {
           log.info("acquire lock");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Lockd(name="foo-service", acquireTimeout=2, lockTimeoutStrategy = LockTimeoutStrategy.KEEP_ACQUIRE)
    public void foo3() {
        try {
            TimeUnit.SECONDS.sleep(2);
           log.info("acquire lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Lockd(name="foo-service",
            acquireTimeout=2,
            customLockTimeoutStrategy = "customLockTimeout")
    public String foo4(String foo, String bar) {
        try {
            TimeUnit.SECONDS.sleep(2);
           log.info("acquire lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "foo4";
    }

    private String customLockTimeout(String foo, String bar) {

       log.info("customLockTimeout foo: " + foo + " bar: " + bar);
        return "custom foo: " + foo + " bar: " + bar;
    }


    @Lockd(name="foo-service", acquireTimeout=10)
    public void foo5(String foo, String bar) {
        try {
            TimeUnit.SECONDS.sleep(2);
           log.info("acquire lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Lockd(name="foo-service", expireTime =10, acquireTimeout = 10000)
    public void foo6(String foo, String bar) {
        try {
            TimeUnit.SECONDS.sleep(2);
           log.info("acquire lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Lockd(name="foo-service", expireTime =1, acquireTimeout = 10000, releaseTimeoutStrategy = ReleaseTimeoutStrategy.FAIL_FAST)
    public void foo7(String foo, String bar) {
        try {
            TimeUnit.SECONDS.sleep(2);
           log.info("acquire lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Lockd(name="foo-service", expireTime =1, acquireTimeout = 10000, customReleaseTimeoutStrategy = "customReleaseTimeout")
    public String foo8(String foo, String bar) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "foo8";
    }

    private String customReleaseTimeout(String foo, String bar) {

        throw new IllegalStateException("customReleaseTimeout");
    }
}
