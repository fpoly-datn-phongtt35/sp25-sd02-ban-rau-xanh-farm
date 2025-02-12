package com.example.datnv1.Entity.Orders;

import com.example.datnv1.Entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends BaseEntity {

    @Column
    private String code;
    @Column
    private String nameCustomer;
    @Column
    private String phoneCustomer;
    @Column
    private String address;
    @Column
    private String note;
    @Column
    private float totalQuantity;
    @Column
    private LocalDateTime dateOfReceipt;
    @Column
    private String shipCode;
    @Column
    private String shipType;
    @Column
    private String shipPrice;
    @Column
    private float totalAmount;

    @Column
    private String status;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetailList = new ArrayList<>();


}
