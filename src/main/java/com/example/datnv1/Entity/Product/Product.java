package com.example.datnv1.Entity.Product;


import com.example.datnv1.Entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long untilId;

    @Transient
    private String unitName;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToMany(mappedBy = "product")
    private Set<Batch> batchSet = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Image> imageSet = new HashSet<>();

    public String getUnitName() {
        return unit.getName();
    }
}
