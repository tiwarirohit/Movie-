package com.capgemini.onlinemoviebooking.exception;

public class ErrorInfo {
	private String message;

	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorInfo(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
