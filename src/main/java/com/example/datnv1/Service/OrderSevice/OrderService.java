package com.example.datnv1.Service.OrderSevice;

import com.example.datnv1.DTO.Req.Orders.OrderDetailsReqDTO;
import com.example.datnv1.DTO.Req.Orders.OrderReqDTO;
import com.example.datnv1.Entity.Orders.OrderDetail;
import com.example.datnv1.Entity.Orders.Orders;
import com.example.datnv1.Enum.OrderStatus;
import com.example.datnv1.Repository.OrderRepository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderDetailService orderDetailService;

    public Orders save(OrderReqDTO dto)  {
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
            order.getOrderDetailList().add(orderDetailService.save(item));
        }
        return order;
    }

    public List<Orders> getAll()  {
        return orderRepo.findAll();
    }
}
