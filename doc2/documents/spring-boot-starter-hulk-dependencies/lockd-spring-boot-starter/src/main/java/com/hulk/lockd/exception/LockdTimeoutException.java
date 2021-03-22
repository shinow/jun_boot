package com.hulk.lockd.exception;

/**
 * @author hulk
 * @since 2019/4/16
 **/
public class LockdTimeoutException extends RuntimeException {

    public LockdTimeoutException() {
        super();
    }

    public LockdTimeoutException(String message) {
        super(message);
    }

    public LockdTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
