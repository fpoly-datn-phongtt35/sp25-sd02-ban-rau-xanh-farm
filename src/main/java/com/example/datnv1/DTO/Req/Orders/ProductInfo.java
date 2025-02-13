package com.example.datnv1.DTO.Req.Orders;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfo {
    private Long productId;
    private float quantity;
}
