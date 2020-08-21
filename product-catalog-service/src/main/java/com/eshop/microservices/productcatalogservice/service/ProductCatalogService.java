/**
 * 
 */
package com.eshop.microservices.productcatalogservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.microservices.productcatalogservice.data.ProductCatalogRepository;
import com.eshop.microservices.productcatalogservice.data.ReviewRepository;
import com.eshop.microservices.productcatalogservice.model.Product;

/**
 * @author Avijit Chakraborty
 *
 */
@Service
public class ProductCatalogService {

	@Autowired
	private ProductCatalogRepository productCatalogRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	public List<Product> findAll() {
		return productCatalogRepository.findAll();
	}

	public List<Product> findByNameContaining(String name) {
		return productCatalogRepository.findByNameContaining(name);
	}

	public Product findByName(String name) {
		return productCatalogRepository.findByName(name);
	}

	public Product findOne(Integer id) {
		return productCatalogRepository.findOne(id);
	}

	public Product save(Product product) {
		return productCatalogRepository.save(product);
	}

	public List<Product> save(List<Product> products) {
		return productCatalogRepository.save(products);
	}

	
}
