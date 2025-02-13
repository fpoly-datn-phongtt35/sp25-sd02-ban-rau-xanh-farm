package com.example.datnv1.Repository.OrderRepository;

import com.example.datnv1.Entity.Orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}
