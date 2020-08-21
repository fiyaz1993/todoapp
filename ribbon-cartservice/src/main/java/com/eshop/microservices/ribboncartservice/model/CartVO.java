/**
 * 
 */
package com.eshop.microservices.ribboncartservice.model;

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
