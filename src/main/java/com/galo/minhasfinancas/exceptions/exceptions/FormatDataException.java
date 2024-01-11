package com.galo.minhasfinancas.exceptions.exceptions;

public class FormatDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FormatDataException(String msg) {
		super(msg);
	}

	public FormatDataException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
