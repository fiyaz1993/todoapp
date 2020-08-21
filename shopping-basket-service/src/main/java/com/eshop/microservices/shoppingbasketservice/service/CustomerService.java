/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.microservices.shoppingbasketservice.RibbonConfiguration;
import com.eshop.microservices.shoppingbasketservice.model.Customer;

/**
 * @author Avijit Chakraborty
 *
 */
@FeignClient(name="zuul-edge-server")
@RibbonClient(name="customer-service", configuration = RibbonConfiguration.class)
public interface CustomerService {

	@GetMapping("customer-service/api/customers")
	public @ResponseBody List<Customer> getCustomers();
	
	@GetMapping("customer-service/api/customer")
	public @ResponseBody Customer getCustomerByMailId(@RequestParam("emailId") String emailId);
	
	@GetMapping("customer-service/api/customer/{id}")
	public @ResponseBody Customer getCustomerById(@PathVariable("id") Long id);
}
