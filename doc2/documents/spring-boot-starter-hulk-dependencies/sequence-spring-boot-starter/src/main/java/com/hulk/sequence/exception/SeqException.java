package com.hulk.sequence.exception;

/**
 * 序列号生成异常
 * @author hulk on 2018/1/10.
 */
public class SeqException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SeqException(String message) {
		super(message);
	}

	public SeqException(Throwable cause) {
		super(cause);
	}

}
