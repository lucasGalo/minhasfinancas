package com.galo.minhasfinancas.exceptions.exceptions;

public class ClassNotInstanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClassNotInstanceException(String msg) {
		super(msg);
	}

	public ClassNotInstanceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
