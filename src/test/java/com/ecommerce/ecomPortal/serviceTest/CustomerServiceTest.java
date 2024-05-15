package com.ecommerce.ecomPortal.serviceTest;

import com.ecommerce.ecomPortal.model.Customer;
import com.ecommerce.ecomPortal.repository.CustomerRepository;
import com.ecommerce.ecomPortal.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testFindCustomerById() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer found = customerService.findCustomerById(1L);
        assertEquals(customer, found);
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer saved = customerService.saveCustomer(customer);
        assertNotNull(saved);
        assertEquals("John Doe", saved.getName());
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
