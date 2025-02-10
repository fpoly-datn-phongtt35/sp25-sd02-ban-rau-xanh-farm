package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch extends BaseEntity {

    @Column
    private LocalDate importDate;

    @Column
    private LocalDate outDate;

    @Column
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "batch")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<ProductDetailBatch> productDetailBatches = new HashSet<ProductDetailBatch>();

}
