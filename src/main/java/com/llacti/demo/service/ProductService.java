package com.llacti.demo.service;

import com.llacti.demo.model.Product;
import com.llacti.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product createProduct(Product product){
        product.setProductId(productRepository.getMaxId()+1);
        System.out.println(productRepository.getMaxId());
        return productRepository.save(product);
    }
}
