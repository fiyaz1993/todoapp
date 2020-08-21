/**
 * 
 */
package com.eshop.microservices.customerservice.exception;

/**
 * @author Avijit Chakraborty
 *
 */
public class CustomerNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2590124458680911463L;

	/**
	 * 
	 */
	public CustomerNotFound() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CustomerNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CustomerNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CustomerNotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomerNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
