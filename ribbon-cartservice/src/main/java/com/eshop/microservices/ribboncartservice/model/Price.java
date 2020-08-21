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
public class Price {
	private Integer id;
	private Integer productId;
	private Double price;
	
}
