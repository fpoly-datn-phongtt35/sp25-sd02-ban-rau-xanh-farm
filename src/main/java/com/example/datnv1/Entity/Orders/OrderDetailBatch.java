package com.example.datnv1.Entity.Orders;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Product.Batch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill_details_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailBatch extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "bill_detail_id")
    private OrderDetail orderDetail;

    @Column
    private int quantity;

}
