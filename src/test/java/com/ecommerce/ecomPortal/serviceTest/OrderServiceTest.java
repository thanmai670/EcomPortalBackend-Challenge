package com.ecommerce.ecomPortal.serviceTest;

import com.ecommerce.ecomPortal.model.Customer;
import com.ecommerce.ecomPortal.model.Order;
import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.repository.OrderRepository;
import com.ecommerce.ecomPortal.service.CustomerService;
import com.ecommerce.ecomPortal.service.OrderService;
import com.ecommerce.ecomPortal.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testPlaceOrder() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        Product product = new Product();
        product.setId(1L);
        product.setName("Widget");

        when(customerService.findCustomerById(1L)).thenReturn(customer);
        when(productService.findProductById(1L)).thenReturn(product);
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        Order order = orderService.placeOrder(1L, 1L, 5);

        verify(customerService).findCustomerById(1L);
        verify(productService).findProductById(1L);
        verify(orderRepository).save(any(Order.class));

        assert order.getCustomer().equals(customer);
        assert order.getProduct().equals(product);
        assert order.getQuantity() == 5;
    }
}
