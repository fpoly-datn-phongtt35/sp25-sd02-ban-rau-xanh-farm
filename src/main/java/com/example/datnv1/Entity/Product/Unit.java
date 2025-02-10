package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Unit extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "unit")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Product> productsSet = new HashSet<>();
}
