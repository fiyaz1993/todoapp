/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.controller;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.microservices.shoppingbasketservice.data.ShoppingBasketRepository;
import com.eshop.microservices.shoppingbasketservice.exception.CustomerNotFoundException;
import com.eshop.microservices.shoppingbasketservice.exception.ProductNotFoundException;
import com.eshop.microservices.shoppingbasketservice.exception.ShoppingBasketNotFound;
import com.eshop.microservices.shoppingbasketservice.model.Customer;
import com.eshop.microservices.shoppingbasketservice.model.ErrorModel;
import com.eshop.microservices.shoppingbasketservice.model.Product;
import com.eshop.microservices.shoppingbasketservice.model.ShoppingBasket;
import com.eshop.microservices.shoppingbasketservice.model.ShoppingBasketItem;
import com.eshop.microservices.shoppingbasketservice.service.CustomerService;
import com.eshop.microservices.shoppingbasketservice.service.ProductCatalogService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author Avijit Chakraborty
 *
 */
@RestController
@RequestMapping(value="/api/shopping-basket-service")
@EnableHystrix
public class ShoppingBasketController {

	private static final Logger log = LoggerFactory.getLogger(ShoppingBasketController.class);
	
	@Autowired
	private ShoppingBasketRepository shoppingBasketRepository;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductCatalogService productCatalogService;
	
	@GetMapping(value = "/shoppingBasket" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@HystrixCommand(fallbackMethod="defaultBasket", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") }, ignoreExceptions = {CustomerNotFoundException.class})
	public @ResponseBody ShoppingBasket getBasket(@RequestParam @NotNull @NotEmpty Long customerId) {
		
		log.info("Received request to get the basket for customerId " + customerId);
		
		checkCustomer(customerId);
		
		ShoppingBasket shoppingBasket = shoppingBasketRepository.findByCustomerId(customerId);
		if(shoppingBasket == null) {
			throw new ShoppingBasketNotFound("Shopping basket not found for customerId " + customerId);
		}
		
		return shoppingBasket;
	}
	
	@PostMapping(value = "/shoppingBasket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@HystrixCommand(fallbackMethod="defaultBasket", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") }, ignoreExceptions = {CustomerNotFoundException.class})
	public @ResponseBody ShoppingBasket createBasket(@RequestBody @NotNull @NotEmpty ShoppingBasket shoppingBasket) {
		
		log.info("Received request to create the basket for customerId " + shoppingBasket.getCustomerId());
		
		checkCustomer(shoppingBasket.getCustomerId());
		
		shoppingBasketRepository.save(shoppingBasket);
		
		return shoppingBasket;
	}
	
	@PatchMapping(value = "/shoppingBasket/{shoppingBasketId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@HystrixCommand(fallbackMethod="faultOnAddBasketItems", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") }, ignoreExceptions = {ProductNotFoundException.class, ShoppingBasketNotFound.class})
	public void addShoppingItem(@PathVariable("shoppingBasketId") Long shoppingBasketId, @RequestBody @NotNull @NotEmpty ShoppingBasketItem shoppingBasketItem) {
		
		log.info("Received request to create the basket item for basketId " + shoppingBasketId);
		
		checkProduct(shoppingBasketItem.getProductId());
		
		ShoppingBasket shoppingBasket = shoppingBasketRepository.findById(shoppingBasketId);
		if(shoppingBasket == null) {
			throw new ShoppingBasketNotFound("Basket not found");
		}
		shoppingBasket.setTotalPrice(shoppingBasket.getTotalPrice() + shoppingBasketItem.getPrice());
		
		shoppingBasket.getBasketItems().add(shoppingBasketItem);
		shoppingBasketItem.setShoppingBasketId(shoppingBasket.getId());
		shoppingBasketItem.setShoppingBasket(shoppingBasket);
		
		shoppingBasketRepository.save(shoppingBasket);
	}
	
	@DeleteMapping(value = "/shoppingBasket/{shoppingBasketId}")
	@ResponseStatus(HttpStatus.OK)
	@HystrixCommand(fallbackMethod="faultOnDeleteBasket", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100000") })
	public void deleteBasket(@PathVariable("shoppingBasketId") Long shoppingBasketId) {
		log.info("Received request to delete the basket for shoppingBasketId " + shoppingBasketId);
		
		ShoppingBasket shoppingBasket = shoppingBasketRepository.findById(shoppingBasketId);
		shoppingBasket.getBasketItems().clear();
		
		shoppingBasketRepository.delete(shoppingBasket);
	}
	
	private void checkCustomer(Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		if(customer == null || "defaultemail@gmail.com".equalsIgnoreCase(customer.getEmailId())) {
			throw new CustomerNotFoundException("CustomerId not found");
		}
	}
	
	private void checkProduct(Long productId) {
		Product product = productCatalogService.getProduct(productId); 
		if(product == null || "Default Product".equalsIgnoreCase(product.getName())) {
			throw new ProductNotFoundException("Product not found");
		}
	}
	
	public ShoppingBasket defaultBasket(ShoppingBasket shoppingBasket) {
		
		shoppingBasket.setBasketItems(new ArrayList<ShoppingBasketItem>());
		
		return shoppingBasket;
	}
	
	public ShoppingBasket defaultBasket(Long customerId) {
		
		ShoppingBasket shoppingBasket = new ShoppingBasket();
		shoppingBasket.setCustomerId(customerId);
		shoppingBasket.setDescription("Default Description");
		shoppingBasket.setTotalPrice(new Double(0));
		shoppingBasket.setBasketItems(new ArrayList<ShoppingBasketItem>());
		
		return shoppingBasket;
	}
	
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	public void faultOnAddBasketItems(Long basketId, ShoppingBasketItem shoppingBasketItem) {
		
	}
	
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	public void faultOnDeleteBasket(Long basketId) {
		
	}
	
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handle(CustomerNotFoundException exception) {
        return new ErrorModel(exception.getMessage());
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handle(ProductNotFoundException exception) {
        return new ErrorModel(exception.getMessage());
    }
	
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handle(ShoppingBasketNotFound exception) {
        return new ErrorModel(exception.getMessage());
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModel handle(Exception exception) {
        return new ErrorModel("Service not responding");
    }
	
}
