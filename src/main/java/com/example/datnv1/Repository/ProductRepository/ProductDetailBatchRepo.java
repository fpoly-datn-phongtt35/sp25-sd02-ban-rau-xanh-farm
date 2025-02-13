package com.example.datnv1.Repository.ProductRepository;

import com.example.datnv1.Entity.Product.ProductDetailBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailBatchRepo extends JpaRepository<ProductDetailBatch, Long> {

    @Query("SELECT pdb FROM ProductDetailBatch pdb WHERE pdb.productDetail.id = :productDetailId AND (pdb.quantity - pdb.reservedQuantity) >0 ORDER BY pdb.batch.importDate ASC")
    List<ProductDetailBatch> findFirstByProductDetailAndAvailableStock(@Param("productDetailId") Long productDetailId);


}
