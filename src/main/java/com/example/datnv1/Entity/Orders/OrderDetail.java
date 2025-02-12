package com.example.datnv1.Entity.Orders;


import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.ProductDetail;
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
    private float quantity;
    @Column
    private float price;
    @Column
    private float total;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    @OneToMany(mappedBy = "orderDetail")
    private List<OrderDetailBatch> orderDetailBatchList=new ArrayList<OrderDetailBatch>();

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Orders orders;
}
