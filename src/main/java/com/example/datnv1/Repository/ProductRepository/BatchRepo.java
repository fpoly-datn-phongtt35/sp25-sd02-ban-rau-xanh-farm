package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BatchRepo extends JpaRepository<Batch, Long> {

    Optional<Batch> findByCode(UUID code);
}
