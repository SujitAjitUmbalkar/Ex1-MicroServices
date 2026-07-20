package com.codingshuttle.ecommerce.order_service.dto;

import lombok.Data;

@Data
public class OrderRequestItemDTO
{
    private Long id;
    private Long productId;
    private Integer quantity;

}
