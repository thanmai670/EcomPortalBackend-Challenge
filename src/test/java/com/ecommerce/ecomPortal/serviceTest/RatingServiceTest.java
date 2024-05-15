package com.ecommerce.ecomPortal.serviceTest;

import com.ecommerce.ecomPortal.model.Product;
import com.ecommerce.ecomPortal.model.Rating;
import com.ecommerce.ecomPortal.repository.ProductRepository;
import com.ecommerce.ecomPortal.repository.RatingRepository;
import com.ecommerce.ecomPortal.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RatingServiceTest {

    @Autowired
    private RatingService ratingService;

    @MockBean
    private RatingRepository ratingRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testRateProduct() {
        Product product = new Product();
        product.setId(1L);

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setScore(4);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating createdRating = ratingService.rateProduct(1L, 4);

        assertThat(createdRating.getProduct().getId()).isEqualTo(1L);
        assertThat(createdRating.getScore()).isEqualTo(4);
        verify(ratingRepository).save(any(Rating.class));
    }
}
