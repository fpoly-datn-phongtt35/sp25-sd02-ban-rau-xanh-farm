package com.example.datnv1.DTO.Req.Orders;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderReqDTO {

    private Long customerId;
    private Long employeeId;
    private Long voucherId;
    private String shipType;
    private String note;
    private String address;
    private String customerName;
    private String phoneNumber;
    private LocalDateTime dateOfReceipt;
    private List<OrderDetailsReqDTO> billDetails;
}
