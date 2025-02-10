package com.example.datnv1.Entity.Product;

import com.example.datnv1.Entity.Account.Customer;
import com.example.datnv1.Entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "product_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFavorite extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_lpf")
    private ListProductFavorite list_product_favorite;
}
