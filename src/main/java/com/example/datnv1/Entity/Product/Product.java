package com.example.datnv1.Entity.Product;


import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.example.datnv1.Enum.ProductStatus;
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
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int price;

    @Column
    private String status = ProductStatus.ACTIVE.toString();

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long untilId;

    @Transient
    private String unitName;

    @OneToMany(mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<OrderDetail> orderDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToMany(mappedBy = "product")
    private Set<Batch> batchSet = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Image> imageSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String getUnitName() {
        return unit.getName();
    }
}
