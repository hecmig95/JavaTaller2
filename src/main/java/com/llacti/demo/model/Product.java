package com.llacti.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    private Long productId;
    String productName;
    Double productPrice;
    Integer status;
}
