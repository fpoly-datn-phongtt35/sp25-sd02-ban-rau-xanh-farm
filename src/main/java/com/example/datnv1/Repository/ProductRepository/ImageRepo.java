package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
