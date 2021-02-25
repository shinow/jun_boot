package com.hulk.lockd.exception;

/**
 * @author hulk
 * @since 2019/4/16
 **/
public class LockdInvocationException extends RuntimeException {

    public LockdInvocationException() {
        super();
    }

    public LockdInvocationException(String message) {
        super(message);
    }

    public LockdInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
