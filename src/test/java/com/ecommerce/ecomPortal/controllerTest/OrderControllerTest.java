package com.ecommerce.ecomPortal.controllerTest;

import com.ecommerce.ecomPortal.controller.OrderController;
import com.ecommerce.ecomPortal.dto.OrderDTO;
import com.ecommerce.ecomPortal.model.Order;
import com.ecommerce.ecomPortal.service.OrderService;
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

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testPlaceOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(1L);
        orderDTO.setProductId(1L);
        orderDTO.setQuantity(1);

        Order order = new Order();
        order.setId(1L); // Assuming the ID is set by the service upon saving

        given(orderService.placeOrder(any(Long.class), any(Long.class), any(Integer.class))).willReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(order)));
    }
}
