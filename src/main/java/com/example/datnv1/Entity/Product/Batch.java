package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.example.datnv1.Entity.Orders.OrderDetailBatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch extends BaseEntity {

    @Column
    private String name;

    @Column
    private UUID code;

    @Column
    private LocalDate importDate;

    @Column
    private LocalDate outDate;

    @Column
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Product product;

    @OneToMany(mappedBy = "batch")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<ProductDetailBatch> productDetailBatches = new HashSet<ProductDetailBatch>();

    @OneToMany(mappedBy = "batch")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OrderDetailBatch> orderDetailBatchList = new ArrayList<OrderDetailBatch>();

}
