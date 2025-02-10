package com.example.datnv1.Entity.Account;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Enum.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String idCart;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String note;

    @Enumerated(EnumType.STRING)
    private AccountType role;
}
