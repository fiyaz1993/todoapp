/**
 * 
 */
package com.eshop.microservices.orderservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eshop.microservices.orderservice.model.CartOrder;
import com.eshop.microservices.orderservice.model.CartResponse;
import com.eshop.microservices.orderservice.repository.OrderRepository;

/**
 * @author Avijit Chakraborty
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository oderRepository;
	
	@Value("${cart.service.url}")
	private String cartServiceURL;
	
	public CartOrder submitOrder(String userName) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CartResponse> cartResponse = restTemplate.postForEntity(cartServiceURL + "/order/" + userName, null, CartResponse.class);
		
		CartOrder order = new CartOrder();
		order.setUser(cartResponse.getBody().getUserName());
		order.setAmount(cartResponse.getBody().getItemTotal());
		order.setDate(LocalDateTime.now());
		
		return oderRepository.save(order);
	}
	
}
