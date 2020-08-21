/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.exception;

/**
 * @author  Avijit Chakraborty
 *
 */
public class ShoppingBasketNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9057908673143978398L;

	/**
	 * 
	 */
	public ShoppingBasketNotFound() {
	}

	/**
	 * @param message
	 */
	public ShoppingBasketNotFound(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ShoppingBasketNotFound(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ShoppingBasketNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ShoppingBasketNotFound(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
