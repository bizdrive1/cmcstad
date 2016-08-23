package com.comcast.ad.controller;

public class RespMessage {
	
	public static final int CREATE_SUCCESS = 0;
	public static final int NOT_FOUND = 1;
	public static final int CREATE_FAILED = 2;
	
	
	private String message;
	private int errorCode;
	
	public RespMessage(int errorCode, String msg) {
		this.message = msg;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
