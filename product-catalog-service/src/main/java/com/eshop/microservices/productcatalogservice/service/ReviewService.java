/**
 * 
 */
package com.eshop.microservices.productcatalogservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.microservices.productcatalogservice.data.ReviewRepository;
import com.eshop.microservices.productcatalogservice.model.Product;
import com.eshop.microservices.productcatalogservice.model.Review;

/**
 * @author Avijit Chakraborty
 *
 */
@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Review> getAllReviewsForProduct(Product product) {
		return reviewRepository.findByProduct(product);
	}

	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}	
}
