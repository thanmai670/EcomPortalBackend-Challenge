package com.ecommerce.ecomPortal.controller;

import com.ecommerce.ecomPortal.dto.OrderDTO;
import com.ecommerce.ecomPortal.model.Order;
import com.ecommerce.ecomPortal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.placeOrder(orderDTO.getCustomerId(), orderDTO.getProductId(), orderDTO.getQuantity());
        return ResponseEntity.ok(order);
    }
}