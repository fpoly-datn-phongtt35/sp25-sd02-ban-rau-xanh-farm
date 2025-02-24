package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.ProductDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDetailRepo extends CrudRepository<ProductDetail, Long> {
    long countByIdIn(List<Long> ids);
}
