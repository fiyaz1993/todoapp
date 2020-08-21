/**
 * 
 */
package com.eshop.microservices.ribboncartservice.model;

import lombok.Data;

/**
 * @author home
 *
 */
@Data
public class CartProcessResponse {

	private Double itemTotal;
	private String userName;
}
