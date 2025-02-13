package com.example.datnv1.DTO.Req.Orders;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetaiBatchReqDTO {
    private Long batchId;
    private float quantity;
}
