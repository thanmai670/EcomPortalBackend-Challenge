package com.ecommerce.ecomPortal.repositoryTest;

import com.ecommerce.ecomPortal.model.Rating;
import com.ecommerce.ecomPortal.repository.RatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RatingRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void testSaveRating() {
        Rating rating = new Rating();
        rating.setScore(5);
        Rating savedRating = entityManager.persistFlushFind(rating);
        assertThat(savedRating.getScore()).isEqualTo(5);
    }
}
