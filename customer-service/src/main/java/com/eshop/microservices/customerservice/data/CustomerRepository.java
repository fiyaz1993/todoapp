/**
 * 
 */
package com.eshop.microservices.customerservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.customerservice.model.Customer;

/**
 * @author Avijit Chakraborty
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByEmailId(String emailId);
	
}
