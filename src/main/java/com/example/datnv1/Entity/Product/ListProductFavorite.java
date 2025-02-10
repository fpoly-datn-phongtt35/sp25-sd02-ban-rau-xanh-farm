package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.Account.Customer;
import com.example.datnv1.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "list_product_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListProductFavorite extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private String name;
}
