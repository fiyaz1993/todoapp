/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Entity
@Data
public class Customer {

	@Id
	@Column(name="email_Id")
	private String emailId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="address")
	private String address;
	
}
