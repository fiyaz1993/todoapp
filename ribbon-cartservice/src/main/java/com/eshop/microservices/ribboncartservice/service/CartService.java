/**
 * 
 */
package com.eshop.microservices.ribboncartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eshop.microservices.ribboncartservice.model.Cart;
import com.eshop.microservices.ribboncartservice.model.CartProcessResponse;
import com.eshop.microservices.ribboncartservice.model.CartVO;
import com.eshop.microservices.ribboncartservice.model.Price;
import com.eshop.microservices.ribboncartservice.repository.CartRepository;

/**
 * @author Avijit Chakraborty
 *
 */
@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public CartVO saveCart(CartVO cartVO, String userName) {
		
		CartToVoMapper mapper = new CartToVoMapper();
		Cart cart = mapper.mapToCart(cartVO);
		cart.setUserName(userName);
		
		if(cartVO.getItem().getPrice() == null || cartVO.getItem().getPrice() == 0) {
			Price price = restTemplate.getForObject("http://price-service/api/price/" + cartVO.getItem().getId(), Price.class);
			cart.setItemPrice(price.getPrice());
		}
		cart.setItemTotalPrice(cart.getItemPrice() * cartVO.getQty());
		
		cartRepository.save(cart);
		
		cartVO.getItem().setItemTotal(cart.getItemTotalPrice());
		cartVO.getItem().setPrice(cart.getItemPrice());;
		
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
