package com.example.datnv1.Service.OrderSevice;

import com.example.datnv1.DTO.Req.Orders.OrderDetailsReqDTO;
import com.example.datnv1.DTO.Req.Orders.OrderReqDTO;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.example.datnv1.Entity.Orders.Orders;
import com.example.datnv1.Enum.OrderStatus;
import com.example.datnv1.Repository.OrderRepository.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderDetailService orderDetailService;

    @Transactional
    public Orders handleSavePending(OrderReqDTO dto){
        Orders order = new Orders();
        order.setAddress(dto.getAddress());
        order.setNote(dto.getNote());
        order.setStatus(OrderStatus.PENDING.toString());
        order.setNameCustomer(dto.getCustomerName());
        order.setPhoneCustomer(dto.getPhoneNumber());
        order.setDateOfReceipt(dto.getDateOfReceipt());
        order = orderRepo.save(order);
        for (OrderDetailsReqDTO item: dto.getBillDetails()){
            item.setOrder(order);
            order.getOrderDetailList().add(orderDetailService.handleSaveOrderPending(item));
        }
        return order;
    }
    public Orders handleSaveConfirm(Long orderId){
        Orders order = getById(orderId);
        for (OrderDetail item : order.getOrderDetailList()){
            orderDetailService.handleSaveOrderConfirm(item);
        }
        order.setStatus(OrderStatus.CONFIRM.toString());
        return orderRepo.save(order);
    }
    public Orders changeStatus(Long orderId, OrderStatus newStatus){
        if(newStatus == OrderStatus.PENDING || newStatus == OrderStatus.CONFIRM || newStatus == OrderStatus.CANCELED){
            throw new RuntimeException("Không cho thay đổi!");
        }
        Orders order = getById(orderId);
        order.setStatus(newStatus.toString());
        return orderRepo.save(order);
    }

    public Orders getById(Long orderId){
        return orderRepo.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy hóa đơn"));
    }

    public List<Orders> getAll()  {
        return orderRepo.findAll();
    }
}
