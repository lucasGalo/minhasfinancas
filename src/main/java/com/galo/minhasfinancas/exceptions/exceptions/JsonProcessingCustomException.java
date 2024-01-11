package com.galo.minhasfinancas.exceptions.exceptions;

public class JsonProcessingCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JsonProcessingCustomException(String msg) {
		super(msg);
	}

	public JsonProcessingCustomException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
