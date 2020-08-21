package com.eshop.microservices.ribboncartservice.model;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Data
public class Item {

	private Integer id;
	private String name;
	private Double price;
	private Double itemTotal;
}
