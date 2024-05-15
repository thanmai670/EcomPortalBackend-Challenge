package com.ecommerce.ecomPortal.repository;

import com.ecommerce.ecomPortal.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
