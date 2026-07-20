package com.codingshuttle.ecommerce.inventory_service.dto;

import lombok.Data;

@Data
public class OrderRequestItemDTO
{
    private Long id;
    private Long productId;
    private Integer quantity;
}
