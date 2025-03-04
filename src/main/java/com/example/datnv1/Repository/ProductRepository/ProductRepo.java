package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    long countByIdIn(List<Long> ids);
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}
