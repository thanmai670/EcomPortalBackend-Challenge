package com.ecommerce.ecomPortal.controllerTest;

import com.ecommerce.ecomPortal.controller.ProductController;
import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProductsTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setCategory("Electronics");
        product.setPrice(1200.0);
        List<Product> allProducts = Arrays.asList(product);

        given(productService.findAllProducts()).willReturn(allProducts);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    public void searchProductsTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        product.setCategory("Electronics");
        List<Product> filteredProducts = Arrays.asList(product);

        given(productService.searchProducts("Smartphone", null)).willReturn(filteredProducts);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/search?name=Smartphone")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Smartphone"));
    }

    @Test
    public void recommendProductsTest() throws Exception {
        Product product = new Product();
        product.setId(2L);
        product.setName("Camera");
        product.setCategory("Electronics");
        List<Product> recommendedProducts = Arrays.asList(product);

        given(productService.recommendProducts(1L)).willReturn(recommendedProducts);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/recommend/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Camera"));
    }
}
