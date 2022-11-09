package com.dambroski.webStoreProject.error;

public class UserNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public UserNotFoundException(String message,Throwable cause,boolean enableSuppression, boolean writableStackTracer) {
		super(message,cause,enableSuppression, writableStackTracer);
	}

}
