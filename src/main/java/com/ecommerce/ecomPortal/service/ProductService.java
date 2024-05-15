package com.ecommerce.ecomPortal.service;

import com.ecommerce.ecomPortal.model.Order;
import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.repository.OrderRepository;
import com.ecommerce.ecomPortal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String name, String category) {
        if (name != null && !name.isEmpty() && category != null && !category.isEmpty()) {
            return productRepository.findByNameAndCategory(name, category);
        } else if (name != null && !name.isEmpty()) {
            return productRepository.findByName(name);
        } else if (category != null && !category.isEmpty()) {
            return productRepository.findByCategory(category);
        }
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new IllegalStateException("Product with id " + id + " not found");
        }
    }

    public List<Product> recommendProducts(Long customerId) {

        List<Order> orders = orderRepository.findAllByCustomerId(customerId);


        Set<String> categories = orders.stream()
                .map(order -> order.getProduct().getCategory())
                .collect(Collectors.toSet());


        List<Product> recommendedProducts = new ArrayList<>();
        for (String category : categories) {
            List<Product> productsInCategory = productRepository.findByCategory(category);

            Set<Long> purchasedProductIds = orders.stream()
                    .map(order -> order.getProduct().getId())
                    .collect(Collectors.toSet());
            recommendedProducts.addAll(productsInCategory.stream()
                    .filter(product -> !purchasedProductIds.contains(product.getId()))
                    .toList());
        }

        return recommendedProducts;
    }

}