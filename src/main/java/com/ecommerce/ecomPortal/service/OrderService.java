package com.ecommerce.ecomPortal.service;

import com.ecommerce.ecomPortal.model.Customer;
import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.model.Order;
import com.ecommerce.ecomPortal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    public Order placeOrder(Long customerId, Long productId, int quantity) {
        Customer customer = customerService.findCustomerById(customerId);
        Product product = productService.findProductById(productId);
        if (customer == null || product == null) {
            throw new IllegalStateException("Customer or Product not found");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }
}
