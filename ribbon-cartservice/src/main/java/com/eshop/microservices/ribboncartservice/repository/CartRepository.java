/**
 * 
 */
package com.eshop.microservices.ribboncartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.ribboncartservice.model.Cart;

/**
 * @author Avijit Chakraborty
 *
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUserName(String userName);
}
