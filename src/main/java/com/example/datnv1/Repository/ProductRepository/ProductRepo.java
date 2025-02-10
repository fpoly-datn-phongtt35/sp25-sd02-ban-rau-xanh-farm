package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
