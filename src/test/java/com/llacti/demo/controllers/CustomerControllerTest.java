package com.llacti.demo.controllers;

import com.llacti.demo.model.Customer;
import com.llacti.demo.service.CustomerService;
import com.llacti.demo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    // Prueba para el endpoint /customers (obtener lista de clientes con paginación)
    @Test
    void testGetCustomers() throws Exception {
        Customer customer1 = new Customer(1L, "John Doe", "123 Street");
        Customer customer2 = new Customer(2L, "Jane Smith", "456 Avenue");

        when(customerService.getCustomers(0, 20)).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/customers")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].customerName", is("John Doe")))
                .andExpect(jsonPath("$[1].customerName", is("Jane Smith")));

        verify(customerService, times(1)).getCustomers(0, 20);
    }

    // Prueba para el endpoint /customers/{customerId} (obtener cliente por ID)
    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer(1L, "John Doe", "123 Street");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName", is("John Doe")));

        verify(customerRepository, times(1)).findById(1L);
    }

    // Prueba para el endpoint /customer (crear o actualizar un cliente)
    @Test
    void testCreateOrUpdateCustomer() throws Exception {
        Customer customer = new Customer(1L, "John Doe", "123 Street");
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.of(customer));
        OngoingStubbing<T> tOngoingStubbing;
        tOngoingStubbing = when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        String jsonBody = "{ \"customerId\": 1, \"customerName\": \"John Doe\", \"customerAddress\": \"123 Street\" }";

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName", is("John Doe")))
                .andExpect(jsonPath("$.customerAddress", is("123 Street")));

        verify(customerRepository, times(1)).findByCustomerId(1L);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
