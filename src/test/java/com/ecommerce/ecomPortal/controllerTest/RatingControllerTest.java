package com.ecommerce.ecomPortal.controllerTest;

import com.ecommerce.ecomPortal.dto.RatingRequest;
import com.ecommerce.ecomPortal.model.Rating;
import com.ecommerce.ecomPortal.service.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Test
    public void testRateProduct() throws Exception {
        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setProductId(1L);
        ratingRequest.setScore(5);

        Rating rating = new Rating();
        rating.setId(1L);
        rating.setScore(5);

        given(ratingService.rateProduct(any(Long.class), any(Integer.class))).willReturn(rating);

        mockMvc.perform(post("/products/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(ratingRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(rating)));
    }
}
