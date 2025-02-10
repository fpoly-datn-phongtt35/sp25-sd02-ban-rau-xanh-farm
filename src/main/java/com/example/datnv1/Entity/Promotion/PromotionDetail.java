package com.example.datnv1.Entity.Promotion;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Product.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "promotion_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column
    private Integer quantity;
}
