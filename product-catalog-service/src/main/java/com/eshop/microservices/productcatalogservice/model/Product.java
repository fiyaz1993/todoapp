/**
 * 
 */
package com.eshop.microservices.productcatalogservice.model;

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
public class Product {

	@Id
	@GeneratedValue
	@Column(name="Id")
	private Integer id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
}
