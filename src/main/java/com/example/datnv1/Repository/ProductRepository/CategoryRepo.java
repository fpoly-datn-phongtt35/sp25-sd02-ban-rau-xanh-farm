package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
