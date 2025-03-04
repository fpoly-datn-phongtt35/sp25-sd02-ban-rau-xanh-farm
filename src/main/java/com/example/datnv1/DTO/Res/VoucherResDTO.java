package com.example.datnv1.DTO.Res;

import com.example.datnv1.Enum.DiscountType;
import com.example.datnv1.Enum.VoucherType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VoucherResDTO {
    private String name;
    private String code;
    private Integer quantity;
    private Double maxDiscount;
    private Double minBill;
    private Double value;
    private VoucherType voucherType;
    private DiscountType discountType;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String status;
    private List<Long> productIds;
    private List<Long> customerIds;
}