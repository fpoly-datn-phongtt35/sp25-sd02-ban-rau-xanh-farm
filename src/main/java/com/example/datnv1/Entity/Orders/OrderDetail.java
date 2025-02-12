package com.example.datnv1.Entity.Orders;


import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.ProductDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bill_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends BaseEntity {

    @Column
    private String productName;
    @Column
    private double quantity;
    @Column
    private float price;
    @Column
    private float total;
    @Column
    private String type;
    @Column
    private String status;

    @OneToMany(mappedBy = "orderDetail")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OrderDetailBatch> orderDetailBatchList=new ArrayList<>();

    @OneToMany(mappedBy = "orderDetail")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductDetailOrder> productDetailOrderList=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Orders orders;
}
