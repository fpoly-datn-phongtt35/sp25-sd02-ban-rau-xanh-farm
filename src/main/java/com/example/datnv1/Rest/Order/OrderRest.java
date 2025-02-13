package com.example.datnv1.Rest.Order;

import com.example.datnv1.DTO.ApiResponse;
import com.example.datnv1.DTO.Req.Orders.OrderReqDTO;
import com.example.datnv1.Service.OrderSevice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderRest {

    @Autowired
    OrderService orderService;

    // Test
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder( @RequestBody OrderReqDTO dto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(orderService.save(dto),"Tạo hóa đơn thành công!"));
        }catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(HttpStatus.CONFLICT, e.getMessage(), e.getMessage()));
        }
    }

    @PostMapping("/create-order-pending")
    public ResponseEntity<?> createOrderPending( @RequestBody OrderReqDTO dto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(orderService.handleSavePending(dto),"Tạo hóa đơn thành công!"));
        }catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(HttpStatus.CONFLICT, e.getMessage(), e.getMessage()));
        }
    }
    @GetMapping("/get-all-orders")
    public ResponseEntity<?> getAllOrders() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(orderService.getAll(),"Tạo hóa đơn thành công!"));
        }catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(HttpStatus.CONFLICT, e.getMessage(), e.getMessage()));
        }
    }
}
