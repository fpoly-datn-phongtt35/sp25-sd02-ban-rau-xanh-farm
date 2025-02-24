package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    long countByIdIn(List<Long> ids);
}
