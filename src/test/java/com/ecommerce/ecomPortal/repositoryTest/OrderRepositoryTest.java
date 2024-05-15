package com.ecommerce.ecomPortal.repositoryTest;

import com.ecommerce.ecomPortal.model.Customer;
import com.ecommerce.ecomPortal.model.Order;
import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindAllByCustomerId() {
        // Setup - create a customer, product, and order
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        entityManager.persist(customer);

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("High-end gaming laptop");
        entityManager.persist(product);

        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(1);
        entityManager.persist(order);
        entityManager.flush();

        // Test
        List<Order> foundOrders = orderRepository.findAllByCustomerId(customer.getId());
        assertThat(foundOrders).hasSize(1);
        assertThat(foundOrders.get(0).getCustomer()).isEqualTo(customer);
        assertThat(foundOrders.get(0).getProduct()).isEqualTo(product);
    }
}
