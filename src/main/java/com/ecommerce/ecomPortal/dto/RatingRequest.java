package com.ecommerce.ecomPortal.dto;

public class RatingRequest {
    private Long productId;
    private int score;

    // getters and setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}