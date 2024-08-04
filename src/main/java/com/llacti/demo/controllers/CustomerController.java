package com.llacti.demo.controllers;

import com.llacti.demo.model.Customer;
import com.llacti.demo.repository.CustomerRepository;

import com.llacti.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    @GetMapping("/customers")
    public List<Customer> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return customerService.getCustomers(page,size);
    }
    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    @PostMapping("/customer")
    public Customer createOrUpdateProduct(@RequestBody Customer customer) {
        if (customer.getCustomerId() != null && customerRepository.existsById(customer.getCustomerId())) {
            // Actualización
            Customer existingCustomer = customerRepository.findByCustomerId(customer.getCustomerId()).orElseThrow();
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setCustomerAddress(customer.getCustomerAddress());
            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return updatedCustomer;
        } else {
            // Creación
            return customerService.createCustomer(customer);
        }
    }
}
