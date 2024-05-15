package com.ecommerce.ecomPortal.repository;

import com.ecommerce.ecomPortal.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(Long customerId);
}
