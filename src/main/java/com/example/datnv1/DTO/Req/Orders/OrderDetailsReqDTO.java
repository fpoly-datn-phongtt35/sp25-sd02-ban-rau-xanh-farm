package com.example.datnv1.DTO.Req.Orders;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsReqDTO {

    private Long productDetailId;
    private List<OrderDetaiBatchReqDTO> orderDetaiBatchReqDTOList;
    private Double quantity;

}
