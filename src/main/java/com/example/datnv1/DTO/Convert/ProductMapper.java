package com.example.datnv1.DTO.Convert;

import com.example.datnv1.DTO.Res.ProductResDTO;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.Product;

public class ProductMapper {

    public static ProductResDTO convertProductToDTO(Product product) {

        float quantity = 0;
        float quantityRetail = 0;
        for(Batch item  : product.getBatchSet()){
            quantity += item.getQuantity();
            quantityRetail += item.getQuantityRetail();
        }

//        for(Batch item  : product.getBatchSet()){
//            quantity += item.getQuantity();
//            quantityRetail += item.getQuantityRetail();
//        }
        return ProductResDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .unitName(product.getUnitName())
                .quantity(quantity)
                .quantityRetail(quantityRetail)
                .images(new String[]{"https://placehold.co/50x50"})
                .updatedAt(product.getUpdatedAt())
                .status(product.getStatus())
                .build();
    }
}
