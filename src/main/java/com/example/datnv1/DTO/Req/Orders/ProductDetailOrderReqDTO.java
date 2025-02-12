package com.example.datnv1.DTO.Req.Orders;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailOrderReqDTO {

    private Long productDetailBatchId;
    private int quantity;
}
