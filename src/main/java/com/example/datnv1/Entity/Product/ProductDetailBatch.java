package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Orders.OrderDetailBatch;
import com.example.datnv1.Entity.Orders.ProductDetailOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product_detail_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailBatch extends BaseEntity {

    @Column
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @OneToMany(mappedBy = "productDetailBatch")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductDetailOrder> productDetailOrders = new ArrayList<>();
}
