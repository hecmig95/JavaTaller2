package com.llacti.demo.service;

import com.llacti.demo.model.Customer;
import com.llacti.demo.model.Product;
import com.llacti.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> getCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable).getContent();
    }
    public Customer createCustomer(Customer customer){
        customer.setCustomerId(customerRepository.getMaxId()+1);
        System.out.println(customerRepository.getMaxId());
        return customerRepository.save(customer);
    }
}
