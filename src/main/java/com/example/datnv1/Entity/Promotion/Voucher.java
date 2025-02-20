package com.example.datnv1.Entity.Promotion;

import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Enum.VoucherType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Integer quantity;

    private Double maxDiscount;

    private Double minBill;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private VoucherType type;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
    private String status;
}

