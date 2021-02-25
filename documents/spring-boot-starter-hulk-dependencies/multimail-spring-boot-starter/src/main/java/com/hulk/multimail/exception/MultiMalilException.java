

package com.hulk.multimail.exception;

import lombok.NoArgsConstructor;

/**
 * @author hulk
 * @date 😴2018年06月22日16:21:57
 */
@NoArgsConstructor
public class MultiMalilException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MultiMalilException(String message) {
		super(message);
	}

	public MultiMalilException(Throwable cause) {
		super(cause);
	}

	public MultiMalilException(String message, Throwable cause) {
		super(message, cause);
	}

	public MultiMalilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
