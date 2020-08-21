/**
 * 
 */
package com.eshop.microservices.productcatalogservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Entity
@Data
public class Review {

	@Id
	@GeneratedValue
	private	int reviewId;
	
	private int stars;
	
	private String author;
	
	private String body;
	
	@ManyToOne@JoinColumn(name = "id")
	private Product product;
	
}
