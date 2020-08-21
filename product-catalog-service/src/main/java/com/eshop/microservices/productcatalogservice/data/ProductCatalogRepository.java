package com.eshop.microservices.productcatalogservice.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.productcatalogservice.model.Product;

public interface ProductCatalogRepository extends JpaRepository<Product, Integer> {

	public Product findByName(String prodcutName);
	public List<Product> findByNameContaining(String prodcutName);
}
