package com.codingshuttle.ecommerce.inventory_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestDTO
{
    private Long id;
    private List<OrderRequestItemDTO> items;
    private BigDecimal totalPrice;
}
