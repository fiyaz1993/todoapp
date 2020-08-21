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
import com.eshop.microservices.shoppingbasketservice.model.Product;


/**
 * @author Avijit Chakraborty
 *
 */
@FeignClient(name="zuul-edge-server")
@RibbonClient(name="product-catalog-service", configuration = RibbonConfiguration.class)
public interface ProductCatalogService {

	@GetMapping("product-catalog-service/api/productCatalogs")
	public @ResponseBody List<Product> getProducts();
	
	@GetMapping("product-catalog-service/api/productCatalog")
	public @ResponseBody Product getProductByName(@RequestParam("name") String name);
	
	@GetMapping("product-catalog-service/api/productCatalog/{id}")
	public @ResponseBody Product getProduct(@PathVariable("id") Long id);
}
