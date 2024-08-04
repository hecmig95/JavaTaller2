package com.llacti.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    private Long orderId;

    @OneToMany(mappedBy = "orderId")
    private List<OrderDetail> orderDetails;

    Double totalOrderAmount;
    LocalDate orderDate;
    LocalDate serviceOrderDate;
    Integer status;
    Integer customerId;
}