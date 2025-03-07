package com.example.datnv1.DTO.Req.Product;

import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.Image;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReqDTO {
    private String name;
    private int price;
    private String description;
    private Long unitId;
    private Long categoryId;
    private Set<Batch> batchSet;
    private Set<Image> images;
    private Set<ProductDetailReqDTO> productDetailSet;
}
