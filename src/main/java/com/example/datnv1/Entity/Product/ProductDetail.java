package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail extends BaseEntity {

    @Column
    private String productDetailName;

    @Transient
    private int quantity;

    @Column
    private float weight;

    @Column
    private float sum;

    @Column
    private float price;

    @OneToMany(mappedBy = "productDetail")
    private Set<ProductDetailBatch> productDetailBatches = new HashSet<>();

    @OneToMany(mappedBy = "productDetail")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<OrderDetail> orderDetailList = new ArrayList<>();

}
