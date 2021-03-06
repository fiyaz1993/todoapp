/**
 * 
 */
package com.eshop.microservices.ribboncartservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.microservices.ribboncartservice.model.CartProcessResponse;
import com.eshop.microservices.ribboncartservice.model.CartVO;
import com.eshop.microservices.ribboncartservice.service.CartService;

/**
 * @author Avijit Chakraborty
 *
 */
@RestController
@RequestMapping(value="/api")
public class CartRibbonController {
	
	private static final Logger log = LoggerFactory.getLogger(CartRibbonController.class);

	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart/{userName}")
	public @ResponseBody CartVO saveCart(@RequestBody CartVO cartVO, @PathVariable String userName) {
		log.info("Inside saveCart");
		
		return cartService.saveCart(cartVO, userName);
	} 
	
	@PostMapping("/cart/order/{userName}")
	public @ResponseBody CartProcessResponse processCartItems(@PathVariable String userName) {
		log.info("Inside processCartItems");
		
		return cartService.processCartItems(userName);
	}

}
