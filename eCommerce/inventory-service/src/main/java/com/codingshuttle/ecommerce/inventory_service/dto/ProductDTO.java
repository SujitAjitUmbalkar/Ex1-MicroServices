package com.codingshuttle.ecommerce.inventory_service.dto;

import lombok.Data;

@Data
public class ProductDTO
{
    private Long id;

    private String title;

    private Double price;

    private Integer stock;
}
