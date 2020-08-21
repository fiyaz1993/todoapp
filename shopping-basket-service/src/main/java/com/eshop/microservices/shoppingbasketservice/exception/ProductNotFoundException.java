/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.exception;

/**
 * @author Avijit Chakraborty
 *
 */
public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7839915387618275804L;

	/**
	 * 
	 */
	public ProductNotFoundException() {
	}

	/**
	 * @param message
	 */
	public ProductNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
