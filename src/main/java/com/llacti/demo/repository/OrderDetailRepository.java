package com.llacti.demo.repository;

import com.llacti.demo.model.OrderDetail;
import com.llacti.demo.model.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    @Query(value = "EXEC GetOrderDetails :orderId", nativeQuery = true)
    List<OrderDetail> findOrderDetailsByOrderId(@Param("orderId") Long orderId);
}