package com.example.datnv1.DTO.Res;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResDTO {
    private Long id;
    private String name;
    private LocalDateTime updatedAt;
    private String description;
    private String[] images = new String[]{"https://placehold.co/50x50"};
    private float price;
    private String unitName;
    private float quantity;
    private float quantityRetail;
    private String status;
}
