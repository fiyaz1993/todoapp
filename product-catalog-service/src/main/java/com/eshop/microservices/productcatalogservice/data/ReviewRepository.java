/**
 * 
 */
package com.eshop.microservices.productcatalogservice.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.productcatalogservice.model.Product;
import com.eshop.microservices.productcatalogservice.model.Review;

/**
 * @author Avijit Chakraborty
 *
 */
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public List<Review> findByProduct(Product product);
}
