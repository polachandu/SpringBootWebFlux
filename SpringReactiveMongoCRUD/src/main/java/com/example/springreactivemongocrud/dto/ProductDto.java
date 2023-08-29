package com.example.springreactivemongocrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    
    private String id;
    private String name;
    private String qty;
    private String price;
}
