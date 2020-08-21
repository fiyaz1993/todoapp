/**
 * 
 */
package com.eshop.microservices.orderservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.microservices.orderservice.model.CartOrder;
import com.eshop.microservices.orderservice.service.OrderService;

/**
 * @author Avijit Chakraborty
 *
 */
@RestController
@RequestMapping(value="/api")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService oderService;
	
	@PostMapping("/orders/{userName}")
	public @ResponseBody CartOrder submitOrder(@PathVariable String userName) {
		log.info("Inside submitOrder");
		
		return oderService.submitOrder(userName);
	} 
	
}
