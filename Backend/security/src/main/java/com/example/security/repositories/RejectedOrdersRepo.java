package com.example.security.repositories;

import com.example.security.entities.OrderDetails;
import com.example.security.entities.RejectedOrders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RejectedOrdersRepo extends JpaRepository<RejectedOrders,Integer> {

    @Query(value = "SELECT * FROM order_details  WHERE status=-1 Order by profit_point DESC", nativeQuery = true)
    List<OrderDetails> findRejecteedOrders();
}
