package com.eccomerce.ecomPortal.repositoryTest;

import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByName() {
        Product product = new Product();
        product.setName("Test Product");
        entityManager.persist(product);
        entityManager.flush();

        Product found = productRepository.findByName("Test Product").get(0);
        assertThat(found.getName()).isEqualTo(product.getName());
    }
}
