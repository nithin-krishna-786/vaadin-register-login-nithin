package com.nithin.vaadinregisterloginnithin.exceptions;

public class UserNotFoundException extends RuntimeException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -8603501939461107752L;

	public UserNotFoundException(String message) {
	        super(message);
	    }
}
