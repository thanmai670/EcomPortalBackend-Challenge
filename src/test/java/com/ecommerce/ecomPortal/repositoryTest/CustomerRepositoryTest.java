package com.ecommerce.ecomPortal.repositoryTest;

import com.ecommerce.ecomPortal.model.Customer;
import com.ecommerce.ecomPortal.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        Customer saved = customerRepository.save(customer);
        assertNotNull(saved.getId());
        assertEquals("John Doe", saved.getName());
    }
}
