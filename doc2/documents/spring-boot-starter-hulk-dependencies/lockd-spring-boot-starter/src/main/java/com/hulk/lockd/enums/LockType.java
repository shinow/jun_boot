package com.hulk.lockd.enums;

/**
 * @author  hulk
 * 锁类型
 */
public enum LockType {
    /**
     * 可重入锁
     */
    REENTRANT,
    /**
     * 公平锁
     */
    FAIR,
    /**
     * 读锁
     */
    READ,
    /**
     * 写锁
     */
    WRITE;

    LockType() {
    }

}
