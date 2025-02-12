package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Orders.OrderDetailBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailBatchRepo extends JpaRepository<OrderDetailBatch, Long> {
}
