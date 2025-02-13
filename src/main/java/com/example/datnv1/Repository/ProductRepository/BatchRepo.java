package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BatchRepo extends JpaRepository<Batch, Long> {

    Optional<Batch> findByCode(UUID code);

    @Query("SELECT b FROM Batch b WHERE b.product.id = :productId AND (b.quantity - b.reservedQuantity) > 0 ORDER BY b.importDate ASC")
    List<Batch> findAvailableBatchesByProduct(@Param("productId") Long productId);

}
