/**
 * 
 */
package com.eshop.microservices.cartservice.model;

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
