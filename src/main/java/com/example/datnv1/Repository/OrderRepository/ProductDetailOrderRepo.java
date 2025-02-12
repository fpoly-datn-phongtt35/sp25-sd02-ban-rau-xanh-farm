package com.example.datnv1.Repository.OrderRepository;

import com.example.datnv1.Entity.Orders.ProductDetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailOrderRepo extends JpaRepository<ProductDetailOrder, Long> {
}
