package com.mos.eboot.tools.shiro.exception;

import org.apache.shiro.authc.AccountException;

public class MustPasswordException extends AccountException {

	private static final long serialVersionUID = -8704675899092830291L;

	/**
	 * Creates a new UnknownAccountException.
	 */
	public MustPasswordException() {
		super();
	}

	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param message
	 *            the reason for the exception
	 */
	public MustPasswordException(String message) {
		super(message);
	}

	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param cause
	 *            the underlying Throwable that caused this exception to be thrown.
	 */
	public MustPasswordException(Throwable cause) {
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
	public MustPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

}
