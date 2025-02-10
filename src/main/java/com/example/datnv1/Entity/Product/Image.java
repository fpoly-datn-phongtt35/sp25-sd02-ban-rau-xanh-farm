package com.example.datnv1.Entity.Product;


import com.example.datnv1.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Product product;
}
