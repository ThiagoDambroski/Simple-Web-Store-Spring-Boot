package com.dambroski.webStoreProject.error;

public class InvalidRequestException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public InvalidRequestException() {
		
	}

	public InvalidRequestException(String message) {
		super(message);
	}

	public InvalidRequestException(String message,Throwable cause) {
		super(message,cause);
	}

	public InvalidRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}

	
	
}


