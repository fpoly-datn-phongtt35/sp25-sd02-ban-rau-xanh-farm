package com.example.datnv1.DTO.Res;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResDTO {
    private Long id;
    private String name;
    private String description;
    private Set<String> image;
    private float price;
    private String unitName;
}
