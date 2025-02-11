package com.example.datnv1.Entity.Cart;

import com.example.datnv1.Entity.Account.Customer;
import com.example.datnv1.Entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
