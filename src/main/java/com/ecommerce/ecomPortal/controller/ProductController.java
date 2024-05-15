package com.ecommerce.ecomPortal.controller;

import com.ecommerce.ecomPortal.dto.RatingRequest;
import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.model.Rating;
import com.ecommerce.ecomPortal.service.ProductService;
import com.ecommerce.ecomPortal.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam(required = false) String name, @RequestParam(required = false) String category) {
        System.out.println("Searching for name: " + name + ", category: " + category);
        return productService.searchProducts(name, category);
    }

    @PostMapping("/rate")
    public ResponseEntity<?> rateProduct(@RequestBody RatingRequest ratingRequest) {
        try {
            Rating ratedProduct = ratingService.rateProduct(ratingRequest.getProductId(), ratingRequest.getScore());
            return ResponseEntity.ok(ratedProduct);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/recommend/{customerId}")
    public ResponseEntity<List<Product>> recommendProducts(@PathVariable Long customerId) {
        try {
            List<Product> recommendedProducts = productService.recommendProducts(customerId);
            if (recommendedProducts.isEmpty()) {
                return ResponseEntity.noContent().build();  // Return 204 if no content is available
            }
            return ResponseEntity.ok(recommendedProducts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();  // Return 500 in case of errors
        }
    }

}
