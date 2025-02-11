package com.example.datnv1.DTO.Req.Product;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailBatchReqDTO {

    private UUID batchCode;
    private Long quatity;
}
