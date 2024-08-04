package com.llacti.demo.controllers;

import com.llacti.demo.model.Order;
import com.llacti.demo.model.OrderDetail;
import com.llacti.demo.repository.OrderRepository;
import com.llacti.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orders")
    public List<Order> getOrderDetail(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "20") int size) {
        return  orderService.getOrders(page,size);
    }
}
