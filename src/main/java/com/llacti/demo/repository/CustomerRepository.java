package com.llacti.demo.repository;

import com.llacti.demo.model.Customer;
import com.llacti.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT COALESCE(MAX(u.customerId), 0) FROM Customer u")
    Long getMaxId();
    Optional<Customer> findByCustomerId(Long customerId);
}
