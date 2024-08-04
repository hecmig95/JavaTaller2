package com.llacti.demo.service;

import com.llacti.demo.model.Customer;
import com.llacti.demo.model.Order;
import com.llacti.demo.repository.CustomerRepository;
import com.llacti.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public List<Order> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable).getContent();
    }
}
