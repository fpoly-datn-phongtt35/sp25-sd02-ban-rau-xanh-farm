package com.example.datnv1.Entity.Account;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Cart.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String note;

    @OneToOne(mappedBy = "customer")
    private Cart cart;
}
