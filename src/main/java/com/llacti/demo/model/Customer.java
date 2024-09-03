package com.llacti.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {
    @Id
    private Long customerId;
    String customerName;
    String customerAddress;

    public Customer(long l, String johnDoe, String s) {
    }

    public Customer() {

    }
}
