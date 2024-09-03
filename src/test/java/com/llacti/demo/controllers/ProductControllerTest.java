package com.llacti.demo.controllers;

import org.springframework.test.web.servlet.MockMvc;

/*public class ProductControllerTest {}*/




import com.llacti.demo.model.Product;
import com.llacti.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testGetProducts() throws Exception {
        Product product1 = new Product(1L, "Product 1", 1, 10.0);
        Product product2 = new Product(2L, "Product 2", 1, 20.0);
        when(productService.getProductsByCriteria("Product", 1)).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/products")
                        .param("productName", "Product")
                        .param("status", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productName", is("Product 1")))
                .andExpect(jsonPath("$[1].productName", is("Product 2")));

        verify(productService, times(1)).getProductsByCriteria("Product", 1);
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product 1", 1, 10.0);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is("Product 1")));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testCreateOrUpdateProduct() throws Exception {
        Product product = new Product(1L, "Product 1", 1, 10.0);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        String jsonBody = "{ \"productId\": 1, \"productName\": \"Product 1\", \"status\": 1, \"productPrice\": 10.0 }";

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is("Product 1")))
                .andExpect(jsonPath("$.productPrice", is(10.0)));

        verify(productService, times(1)).createProduct(any(Product.class));
    }
}
