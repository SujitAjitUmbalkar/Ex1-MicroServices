package com.codingshuttle.ecommerce.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdersDTO
{
    private Long id;
    private List<OrderItemDTO> items;
    private BigDecimal totalPrice;

}
