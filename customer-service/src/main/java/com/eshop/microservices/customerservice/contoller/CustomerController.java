/**
 * 
 */
package com.eshop.microservices.customerservice.contoller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.microservices.customerservice.data.CustomerRepository;
import com.eshop.microservices.customerservice.exception.CustomerNotFound;
import com.eshop.microservices.customerservice.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author Avijit Chakraborty
 *
 */
@RestController
@RequestMapping(value="/api")
@EnableHystrix
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	@HystrixCommand(fallbackMethod="defaultCustomerList", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") })
	public @ResponseBody List<Customer> getCustomers() {
		log.info("Inside getCustomers");
		return customerRepository.findAll();
	}
	
	
	@GetMapping("/customer")
	@HystrixCommand(fallbackMethod="defaultCustomer", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public @ResponseBody Customer getCustomerByMailId(@RequestParam String emailId) {
		log.info("Inside getCustomer");
		Customer customer = customerRepository.findByEmailId(emailId);
		if(customer == null) {
			throw new CustomerNotFound("Customer not found");
		}
		
		return customer;
	}
	
	@GetMapping("/customer/{id}")
	@HystrixCommand(fallbackMethod="defaultCustomer", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public @ResponseBody Customer getCustomerById(@PathVariable Long id) {
		log.info("Inside getCustomerById");
		Customer customer = customerRepository.findOne(id);
		if(customer == null) {
			throw new CustomerNotFound("Customer not found");
		}
		
		return customer;
	}
	
	public List<Customer> defaultCustomerList() {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(defaultCustomer(new Long(0)));
		
		return customerList;
	}
	
	public Customer defaultCustomer(Long id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setEmailId("defaultemail@gmail.com");
		customer.setFirstName("NoName");
		customer.setLastName("NoName");
		customer.setAddress("Default address");
		return customer;
	}
	
	public Customer defaultCustomer(String emailId) {
		Customer customer = new Customer();
		customer.setId(new Long(0));
		customer.setEmailId(emailId);
		customer.setFirstName("NoName");
		customer.setLastName("NoName");
		customer.setAddress("Default address");
		return customer;
	}
	
//    @ExceptionHandler(CustomerNotFound.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorModel handle(CustomerNotFound exception) {
//        return new ErrorModel(exception.getMessage());
//    }
}
