package com.eshop.microservices.shoppingbasketservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.shoppingbasketservice.model.ShoppingBasket;

public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasket, Long> {

	public ShoppingBasket findById(Long customerId);
	public ShoppingBasket findByCustomerId(Long customerId);
	
}
