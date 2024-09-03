package com.llacti.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_details")
@IdClass(OrderDetailId.class)
public class OrderDetail {
    Double amount;
    Double totalPrice;
    @Id
    Long orderId;
    @Id
    Long productId;

    public OrderDetail(long l, long l1, String productA, int i) {
    }

    public OrderDetail() {

    }
}
