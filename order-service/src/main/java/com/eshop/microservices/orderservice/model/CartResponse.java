/**
 * 
 */
package com.eshop.microservices.orderservice.model;

import lombok.Data;

/**
 * @author home
 *
 */
@Data
public class CartResponse {

	private Double itemTotal;
	private String userName;
}
