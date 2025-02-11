package com.example.datnv1.DTO.Req.Product;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailReqDTO {

    private String name;
    private float weight;
    private Set<ProductDetailBatchReqDTO> productDetailBatch = new HashSet<>();
}
