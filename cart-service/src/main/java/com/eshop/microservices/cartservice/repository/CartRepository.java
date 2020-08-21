/**
 * 
 */
package com.eshop.microservices.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.cartservice.model.Cart;

/**
 * @author Avijit Chakraborty
 *
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUserName(String userName);
}
