package com.llacti.demo.controllers;

import com.llacti.demo.model.OrderDetail;
import com.llacti.demo.model.Product;
import com.llacti.demo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailController {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @GetMapping("/orderDetails")
    public List<OrderDetail> getOrderDetail() {
        return  orderDetailRepository.findAll();
    }

    @GetMapping("/orderDetailsWithStoreProcedure")
    public List<OrderDetail> getOrderDetailWithStoreProcedure(@RequestParam Long id) {
        return orderDetailRepository.findOrderDetailsByOrderId(id);
    }


}
