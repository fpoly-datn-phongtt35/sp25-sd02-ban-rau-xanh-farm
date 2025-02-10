package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepo extends JpaRepository<Batch, Long> {
}
