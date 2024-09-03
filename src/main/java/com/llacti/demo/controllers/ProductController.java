package com.llacti.demo.controllers;

import com.llacti.demo.model.Product;
import com.llacti.demo.repository.ProductRepository;
import com.llacti.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    //Endpoint 1
    @GetMapping("/products")
    public List<Product> getProducts(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "status", required = false) Integer status) {
        return productRepository.findByProductNameAndStatus(productName, status);
    }
    //Endpoint2
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productRepository.findById(productId).get();
    }

    //Endpoint3
    @PostMapping("/product")
    public Product createOrUpdateProduct(@RequestBody Product product) {
        // Si el producto tiene un ID, se considera una actualización
        System.out.println(product.getProductId());
        if (product.getProductId() != null && productRepository.existsById(product.getProductId())) {
            // Actualización
            Product existingProduct = productRepository.findByProductId(product.getProductId()).orElseThrow();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setStatus(product.getStatus());
            existingProduct.setProductPrice(product.getProductPrice());
            Product updatedProduct = productRepository.save(existingProduct);
            return updatedProduct;
        } else {
            // Creación
            return productService.createProduct(product);
        }
    }


}
