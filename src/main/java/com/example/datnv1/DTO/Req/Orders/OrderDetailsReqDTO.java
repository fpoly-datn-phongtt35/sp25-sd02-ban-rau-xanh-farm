package com.example.datnv1.DTO.Req.Orders;

import com.example.datnv1.DTO.Req.Product.ProductDetailBatchReqDTO;
import com.example.datnv1.Entity.Orders.Orders;
import com.example.datnv1.Enum.SellType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsReqDTO {

    private List <ProductDetailOrderReqDTO> productDetails;
    private List<OrderDetaiBatchReqDTO> batches;
    private ProductInfo productInfo;
    private Orders order;
    private SellType type;

    public List <ProductDetailOrderReqDTO> getProductDetails() {
        if(productDetails == null) return new ArrayList<>();
        return productDetails;
    }

    public List<OrderDetaiBatchReqDTO> getBatches() {
        if(batches == null) return new ArrayList<>();
        return batches;
    }
}
