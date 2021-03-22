package com.hulk.Idempotent.exception;

import lombok.NoArgsConstructor;

/**
 * @author hulk
 * @date 😴2018年06月22日16:21:57
 */
@NoArgsConstructor
public class IdempotentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IdempotentException(String message) {
		super(message);
	}

	public IdempotentException(Throwable cause) {
		super(cause);
	}

	public IdempotentException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdempotentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
