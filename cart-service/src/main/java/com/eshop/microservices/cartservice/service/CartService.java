/**
 * 
 */
package com.eshop.microservices.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.microservices.cartservice.model.Cart;
import com.eshop.microservices.cartservice.model.CartProcessResponse;
import com.eshop.microservices.cartservice.model.CartVO;
import com.eshop.microservices.cartservice.repository.CartRepository;

/**
 * @author Avijit Chakraborty
 *
 */
@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	public CartVO saveCart(CartVO cartVO, String userName) {
		
		CartToVoMapper mapper = new CartToVoMapper();
		Cart cart = mapper.mapToCart(cartVO);
		cart.setUserName(userName);
		
		cartRepository.save(cart);
		
		cartVO.getItem().setItemTotal(cart.getItemTotalPrice());
		
		return cartVO;
	}
	
	public CartVO getCartById(Integer cartId) {
		
		Cart cart = cartRepository.findOne(cartId);
		
		CartToVoMapper mapper = new CartToVoMapper();
		CartVO cartVO = mapper.mapToCartVo(cart);
		
		return cartVO;
	}
	
	public CartProcessResponse processCartItems(String userName) {
		
		List<Cart> carts = cartRepository.findByUserName(userName);
		
		CartProcessResponse cartProcessResponse = new CartProcessResponse();
		Double itemTotal = 0d;
		
		if(carts != null && !carts.isEmpty()) {
			for(Cart cart : carts) {
				itemTotal += cart.getItemTotalPrice();
			}
		}
		
		cartProcessResponse.setItemTotal(itemTotal);
		cartProcessResponse.setUserName(userName);
		
		return cartProcessResponse;
	}
}
