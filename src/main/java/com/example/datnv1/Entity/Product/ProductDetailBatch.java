package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "product_detail_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailBatch extends BaseEntity {

    @Column
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;
}
