package com.llacti.demo.controllers;

import com.llacti.demo.model.OrderDetail;
import com.llacti.demo.repository.OrderDetailRepository;
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

class OrderDetailControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @InjectMocks
    private OrderDetailController orderDetailController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderDetailController).build();
    }

    // Prueba para el endpoint /orderDetails (obtener todos los detalles de orden)
    @Test
    void testGetOrderDetails() throws Exception {
        OrderDetail orderDetail1 = new OrderDetail(1L, 1L, "Product A", 2);
        OrderDetail orderDetail2 = new OrderDetail(2L, 1L, "Product B", 1);

        when(orderDetailRepository.findAll()).thenReturn(Arrays.asList(orderDetail1, orderDetail2));

        mockMvc.perform(get("/orderDetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productName", is("Product A")))
                .andExpect(jsonPath("$[1].productName", is("Product B")));

        verify(orderDetailRepository, times(1)).findAll();
    }

    // Prueba para el endpoint /orderDetailsWithStoreProcedure (obtener detalles de orden por ID)
    @Test
    void testGetOrderDetailWithStoreProcedure() throws Exception {
        OrderDetail orderDetail = new OrderDetail(1L, 1L, "Product A", 2);
        when(orderDetailRepository.findOrderDetailsByOrderId(1L)).thenReturn(Arrays.asList(orderDetail));

        mockMvc.perform(get("/orderDetailsWithStoreProcedure")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productName", is("Product A")));

        verify(orderDetailRepository, times(1)).findOrderDetailsByOrderId(1L);
    }
}
