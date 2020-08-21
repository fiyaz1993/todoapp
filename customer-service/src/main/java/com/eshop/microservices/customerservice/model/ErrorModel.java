/**
 * 
 */
package com.eshop.microservices.customerservice.model;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Data
public class ErrorModel {

	private String errorMessage;
	
	/**
	 * 
	 */
	public ErrorModel(String errorMessage) {
		this.errorMessage =errorMessage;
	}

	
	
}
