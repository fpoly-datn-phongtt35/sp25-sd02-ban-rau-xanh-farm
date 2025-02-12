package com.example.datnv1.Service.OrderSevice;


import com.example.datnv1.DTO.Req.Orders.OrderDetaiBatchReqDTO;
import com.example.datnv1.DTO.Req.Orders.OrderDetailsReqDTO;
import com.example.datnv1.DTO.Req.Orders.ProductDetailOrderReqDTO;
import com.example.datnv1.DTO.Req.Product.ProductDetailBatchReqDTO;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.example.datnv1.Entity.Orders.OrderDetailBatch;
import com.example.datnv1.Entity.Orders.ProductDetailOrder;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.ProductDetailBatch;
import com.example.datnv1.Enum.SellType;
import com.example.datnv1.Repository.OrderRepository.OrderDetailRepo;
import com.example.datnv1.Repository.OrderRepository.ProductDetailOrderRepo;
import com.example.datnv1.Repository.ProductRepository.OrderDetailBatchRepo;
import com.example.datnv1.Repository.ProductRepository.ProductDetailBatchRepo;
import com.example.datnv1.Service.ProductService.BatchSevice;
import com.example.datnv1.Service.ProductService.ProductDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    BatchSevice batchSevice;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    OrderDetailBatchRepo orderDetailBatchRepo;
    @Autowired
    ProductDetailOrderRepo productDetailOrderRepo;

    @Transactional
    public OrderDetail save(OrderDetailsReqDTO dto) {

        if(!dto.getBatches().isEmpty() && !dto.getProductDetails().isEmpty()) {
            throw new RuntimeException("Chỉ có thể mua theo cân hoặc sản phẩm chi tiết!");
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetailRepo.save(orderDetail);
        String productName = "";
        float price = 0;
        double quantity = 0;
        String type = SellType.PRODUCT_DETAIL.toString();
        if(!dto.getBatches().isEmpty()) {
            type = SellType.RETAIL.toString();
            for (OrderDetaiBatchReqDTO item : dto.getBatches()){
                Batch batch = batchSevice.getById(item.getBatchId());                                                                                                                                                if(productName.isEmpty()) {
                    productName = batch.getProduct().getName();
                    price = batch.getProduct().getPrice();
                }
                quantity += item.getQuantity();
                orderDetailBatchRepo.save(OrderDetailBatch.builder()
                                .orderDetail(orderDetail)
                                .batch(batch)
                                .quantity(item.getQuantity())
                                .build()
                );
            }
        }

        if(!dto.getProductDetails().isEmpty()) {
            for (ProductDetailOrderReqDTO item : dto.getProductDetails()){
                ProductDetailBatch productDetailBatch = productDetailService
                        .getProductDetailBatchById(item.getProductDetailBatchId());
                if(productName.isEmpty()) {
                    productName = productDetailBatch.getProductDetail().getProductDetailName();
                    price = productDetailBatch.getProductDetail().getPrice();
                }
                quantity += item.getQuantity();
                productDetailOrderRepo.save(ProductDetailOrder.builder()
                                .orderDetail(orderDetail)
                                .productDetailBatch(productDetailBatch)
                                .quantity(item.getQuantity())
                                .build()
                );
            }
        }
        orderDetail.setProductName(productName);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(price);
        orderDetail.setTotal((float)( price * quantity));
        orderDetail.setOrders(dto.getOrder());
        orderDetail.setType(type);
        return orderDetailRepo.save(orderDetail);
    }
}
