package com.example.demo.exception;

public class HealthCareException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public HealthCareException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public HealthCareException(String message) {
		super(message);
		
	}
	
	

}
