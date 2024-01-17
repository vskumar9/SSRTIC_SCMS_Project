package com.exception;

// Create User Exception Class
public class InvalidException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	User Exception Method
	public InvalidException(String message) {
		super(message);
	}

}
