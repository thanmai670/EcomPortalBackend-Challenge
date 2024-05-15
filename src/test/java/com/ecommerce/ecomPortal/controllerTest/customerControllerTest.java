package com.ecommerce.ecomPortal.controllerTest;

import com.ecommerce.ecomPortal.controller.CustomerController;
import com.ecommerce.ecomPortal.model.Customer;
import com.ecommerce.ecomPortal.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class customerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");

        given(customerService.findAllCustomers()).willReturn(Arrays.asList(customer));

        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        given(customerService.findCustomerById(1L)).willReturn(customer);

        mockMvc.perform(get("/customers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");

        given(customerService.saveCustomer(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer existingCustomer = new Customer();
        existingCustomer.setId(1L);
        existingCustomer.setName("John Doe");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1L);
        updatedCustomer.setName("John Updated");

        given(customerService.findCustomerById(1L)).willReturn(existingCustomer);
        given(customerService.saveCustomer(any(Customer.class))).willReturn(updatedCustomer);

        mockMvc.perform(put("/customers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
