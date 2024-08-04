package com.llacti.demo.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailId implements Serializable {
    private Long orderId;
    private Long productId;
}