/**
 * 
 */
package com.eshop.microservices.cartservice.model;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Data
public class CartVO {

	private Item item;
	private Integer qty;
	
}
