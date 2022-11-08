package com.dambroski.webStoreProject.error;

public class ItemNotFoundException extends Exception {

	public ItemNotFoundException() {
		
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}

	public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}

}
