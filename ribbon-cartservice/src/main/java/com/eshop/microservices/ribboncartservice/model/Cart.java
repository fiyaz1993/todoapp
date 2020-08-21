/**
 * 
 */
package com.eshop.microservices.ribboncartservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue
	@Column(name="Id")
	private Integer id;
	
	private Integer itemId;
	
	private String productName;
	
	private Double itemPrice;
	
	private Integer quantity;
	
	private Double itemTotalPrice;
	
	private String userName;
	
}
