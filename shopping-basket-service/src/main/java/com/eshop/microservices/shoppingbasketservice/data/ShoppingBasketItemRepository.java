/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.shoppingbasketservice.model.ShoppingBasketItem;

/**
 * @author Avijit Chakraborty
 *
 */
public interface ShoppingBasketItemRepository extends JpaRepository<ShoppingBasketItem, Long> {

}
