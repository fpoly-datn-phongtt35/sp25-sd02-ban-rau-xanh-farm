package com.example.datnv1.DTO.Convert;

import com.example.datnv1.DTO.Res.ProductResDTO;
import com.example.datnv1.Entity.Product.Product;

public class ProductMapper {

    public static ProductResDTO convertProductToDTO(Product product) {
        return ProductResDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .unitName(product.getUnitName())
                .build();
    }
}
