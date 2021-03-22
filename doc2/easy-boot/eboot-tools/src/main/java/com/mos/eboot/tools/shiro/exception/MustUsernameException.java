package com.mos.eboot.tools.shiro.exception;

import org.apache.shiro.authc.AccountException;

public class MustUsernameException extends AccountException {

	private static final long serialVersionUID = -8704675899092830291L;

	/**
	 * Creates a new UnknownAccountException.
	 */
	public MustUsernameException() {
		super();
	}

	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param message
	 *            the reason for the exception
	 */
	public MustUsernameException(String message) {
		super(message);
	}

	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param cause
	 *            the underlying Throwable that caused this exception to be thrown.
	 */
	public MustUsernameException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param message
	 *            the reason for the exception
	 * @param cause
	 *            the underlying Throwable that caused this exception to be thrown.
	 */
	public MustUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

}
