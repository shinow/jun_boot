package com.hulk.lockd.lock;

/**
 * @author  hulk
 */
public interface Lock {

    boolean acquire();

    boolean release();
}

