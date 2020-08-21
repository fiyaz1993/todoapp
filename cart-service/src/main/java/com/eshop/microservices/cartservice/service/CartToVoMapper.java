/**
 * 
 */
package com.eshop.microservices.cartservice.service;

import com.eshop.microservices.cartservice.model.Cart;
import com.eshop.microservices.cartservice.model.CartVO;
import com.eshop.microservices.cartservice.model.Item;

/**
 * @author Avijit Chakraborty
 *
 */
public class CartToVoMapper {

	public CartVO mapToCartVo(Cart cart) {
		
		CartVO cartVo = new CartVO();
		Item item = new Item();
		
		item.setId(cart.getItemId());
		item.setItemTotal(cart.getItemTotalPrice());
		item.setName(cart.getProductName());
		item.setPrice(cart.getItemPrice());
		
		cartVo.setItem(item);
		cartVo.setQty(cart.getQuantity());
		
		return cartVo;
	}
	
	public Cart mapToCart(CartVO cartVo) {
		
		Cart cart = new Cart();
		
		cart.setItemId(cartVo.getItem().getId());
		cart.setItemPrice(cartVo.getItem().getPrice());
		cart.setItemTotalPrice(cartVo.getItem().getPrice() * cartVo.getQty());
		cart.setProductName(cartVo.getItem().getName());
		cart.setQuantity(cartVo.getQty());
		
		return cart;
		
	}
	
	
}
