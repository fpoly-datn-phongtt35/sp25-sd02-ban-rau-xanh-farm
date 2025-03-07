package com.example.datnv1.Entity.Product;


import com.example.datnv1.Entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Product product;

    @Column
    private int slot;
}
