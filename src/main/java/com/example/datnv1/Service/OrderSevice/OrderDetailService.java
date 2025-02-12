package com.example.datnv1.Service.OrderSevice;


import com.example.datnv1.DTO.Req.Orders.OrderDetailsReqDTO;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Repository.OrderRepository.OrderDetailRepo;
import com.example.datnv1.Service.ProductService.BatchSevice;
import com.example.datnv1.Service.ProductService.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    BatchSevice batchSevice;
    @Autowired
    ProductDetailService productDetailService;

    public OrderDetail save(OrderDetailsReqDTO dto) {

        if(dto.getBatchId() != null && dto.getProductDetailId() != null) {
            throw new RuntimeException("Chỉ có thể mua theo cân hoặc sản phẩm chi tiết!");
        }
        OrderDetail orderDetail = new OrderDetail();
        if(dto.getBatchId() != null) {
            Batch batch = batchSevice.getById(dto.getBatchId());

        }



    }
}
