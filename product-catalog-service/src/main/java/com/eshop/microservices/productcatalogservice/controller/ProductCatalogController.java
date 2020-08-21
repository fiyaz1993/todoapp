/**
 * 
 */
package com.eshop.microservices.productcatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.microservices.productcatalogservice.data.ProductCatalogRepository;
import com.eshop.microservices.productcatalogservice.model.Product;
import com.eshop.microservices.productcatalogservice.model.Review;
import com.eshop.microservices.productcatalogservice.service.ProductCatalogService;
import com.eshop.microservices.productcatalogservice.service.ReviewService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author Avijit Chakraborty
 *
 */
@RestController
@RequestMapping(value="/api")
@EnableHystrix
public class ProductCatalogController {

	private static final Logger log = LoggerFactory.getLogger(ProductCatalogRepository.class);
	
	@Autowired
	private ProductCatalogService productCatalogService;	
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/products")
	@HystrixCommand(fallbackMethod="getDefaultProductList", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") })
	public @ResponseBody List<Product> getProducts() {
		log.info("Inside getProducts");
		
		return productCatalogService.findAll();
	}
	
	
	@GetMapping("/products/byName/{name}")
	@HystrixCommand(fallbackMethod="getDefultProductByName", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") })
	public @ResponseBody List<Product> getProductByName(@PathVariable String name) {
		log.info("Inside getProductByName");
		
		return productCatalogService.findByNameContaining(name);
	}
	
	@GetMapping("/products/{id}")
	@HystrixCommand(fallbackMethod="getDefultProduct", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000") })
	public @ResponseBody Product getProduct(@PathVariable Integer id) {
		log.info("Inside getProduct");
		
		return productCatalogService.findOne(id);
	}
	
	@PostMapping("/products")
	public @ResponseBody Product saveProduct(@RequestBody Product product) {
		log.info("Inside saveProduct");
		
		return productCatalogService.save(product);
	}
	
	@PostMapping("/products/all")
	public @ResponseBody List<Product> saveAllProducts(@RequestBody List<Product> products) {
		log.info("Inside saveAllProducts");
		
		return productCatalogService.save(products);
	}
	
	@GetMapping("/products/{id}/reviews")
	public @ResponseBody List<Review> getAllReviewsForProduct(@PathVariable Integer id) {
		log.info("Inside getAllReviewsForProduct");
		
		Product product = new Product();
		product.setId(id);
		return reviewService.getAllReviewsForProduct(product);
	}
	
	@PostMapping("/products/{id}/reviews")
	public @ResponseBody Review saveReview(@PathVariable Integer id, @RequestBody Review review) {
		log.info("Inside saveReview");
		
		Product product = productCatalogService.findOne(id);
		review.setProduct(product);
		
		return reviewService.saveReview(review);
	}
	
	public List<Product> getDefaultProductList() {
		List<Product> productList = new ArrayList<>();
		productList.add(getDefultProduct(new Integer(0)));
		return productList;
	}
	
	
	public @ResponseBody List<Product> getDefultProductByName(@PathVariable String name) {
		List<Product> listOfProducts = new ArrayList<>();
		Product product = getDefultProduct(new Integer(0));
		product.setName(name);
		
		listOfProducts.add(product);
		
		return listOfProducts;
	}
	
	public @ResponseBody Product getDefultProduct(@PathVariable Integer id) {
		Product product = new Product();
		product.setId(id);
		product.setName("Default Product");
		product.setDescription("Default Prodcut description");
		product.setPrice(new Double(0.0));
		
		return product;
		
	}
}
