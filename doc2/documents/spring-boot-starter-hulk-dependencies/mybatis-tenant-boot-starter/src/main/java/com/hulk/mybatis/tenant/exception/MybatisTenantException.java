package com.hulk.mybatis.tenant.exception;

/**
 * @author hulk
 * @since 2019/4/16
 **/
public class MybatisTenantException extends RuntimeException {

    public MybatisTenantException() {
        super();
    }

    public MybatisTenantException(String message) {
        super(message);
    }

    public MybatisTenantException(String message, Throwable cause) {
        super(message, cause);
    }
}
