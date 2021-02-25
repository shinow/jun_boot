

package com.hulk.limitb.exception;

import lombok.NoArgsConstructor;

/**
 * @author hulk
 * @date 😴2018年06月22日16:21:57
 */
@NoArgsConstructor
public class LimitbException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LimitbException(String message) {
		super(message);
	}

	public LimitbException(Throwable cause) {
		super(cause);
	}

	public LimitbException(String message, Throwable cause) {
		super(message, cause);
	}

	public LimitbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
