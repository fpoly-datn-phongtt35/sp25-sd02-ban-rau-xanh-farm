package com.example.datnv1.DTO.Req.Orders;

import lombok.*;

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
    private String node;
    private String address;
    private String customerName;
    private String phoneNumber;
    private String dateOfReceipt;
    private List<OrderDetailsReqDTO> billDetails;
}
