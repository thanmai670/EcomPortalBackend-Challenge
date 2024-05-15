package com.ecommerce.ecomPortal.repository;

import com.ecommerce.ecomPortal.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(String category);
    List<Product> findByNameAndCategory(String name, String category);
    Optional<Product> findById(Long id);
}