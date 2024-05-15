package com.ecommerce.ecomPortal.service;

import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.model.Rating;
import com.ecommerce.ecomPortal.repository.ProductRepository;
import com.ecommerce.ecomPortal.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductRepository productRepository;

    public Rating rateProduct(Long productId, int score) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found with ID: " + productId));

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setScore(score);
        return ratingRepository.save(rating);
    }
}
