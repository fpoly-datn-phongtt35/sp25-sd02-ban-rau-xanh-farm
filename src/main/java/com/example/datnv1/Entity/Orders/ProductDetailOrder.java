package com.example.datnv1.Entity.Orders;


import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.ProductDetailBatch;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_details_bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailOrder extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_detail_batch_id")
    private ProductDetailBatch productDetailBatch;

    @ManyToOne
    @JoinColumn(name = "bill_detail_id")
    private OrderDetail orderDetail;

    @Column
    private int quantity;
}
