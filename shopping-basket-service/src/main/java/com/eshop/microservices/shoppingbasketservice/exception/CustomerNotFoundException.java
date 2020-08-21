/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.exception;

/**
 * @author Avijit Chakraborty
 *
 */
public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2070559894526320037L;

	/**
	 * 
	 */
	public CustomerNotFoundException() {
	}

	/**
	 * @param message
	 */
	public CustomerNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CustomerNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
