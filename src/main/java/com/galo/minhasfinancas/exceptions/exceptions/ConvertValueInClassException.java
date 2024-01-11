package com.galo.minhasfinancas.exceptions.exceptions;

public class ConvertValueInClassException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConvertValueInClassException(String msg) {
		super(msg);
	}

	public ConvertValueInClassException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
