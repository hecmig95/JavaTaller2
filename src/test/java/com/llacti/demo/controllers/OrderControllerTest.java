package com.llacti.demo.controllers;

import com.llacti.demo.model.Order;
import com.llacti.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    // Prueba para el endpoint /orders (obtener órdenes con paginación)
    @Test
    void testGetOrders() throws Exception {
        Order order1 = new Order();
        Order order2 = new Order();

        when(orderService.getOrders(0, 20)).thenReturn(Arrays.asList(order1, order2));

        mockMvc.perform(get("/orders")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].customerName", is("Customer 1")))
                .andExpect(jsonPath("$[1].customerName", is("Customer 2")));

        verify(orderService, times(1)).getOrders(0, 20);
    }
}
