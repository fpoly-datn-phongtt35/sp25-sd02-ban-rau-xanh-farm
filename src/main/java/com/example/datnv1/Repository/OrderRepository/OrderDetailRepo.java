package com.example.datnv1.Repository.OrderRepository;

import com.example.datnv1.Entity.Orders.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
}
