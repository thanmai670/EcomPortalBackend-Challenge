package com.ecommerce.ecomPortal.serviceTest;

import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.repository.ProductRepository;
import com.ecommerce.ecomPortal.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testFindAllProducts() {
        Product product = new Product();
        product.setName("Gadget");
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.findAllProducts();
        assertFalse(products.isEmpty());
        assertEquals("Gadget", products.get(0).getName());
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.findProductById(1L);
        assertEquals(1L, found.getId());
    }

}
