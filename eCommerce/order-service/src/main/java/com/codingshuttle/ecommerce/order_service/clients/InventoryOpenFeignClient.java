package com.codingshuttle.ecommerce.order_service.clients;


import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", path = "/api/v1")
public interface InventoryOpenFeignClient
{
    @PutMapping("/products/reduce-stock")
    Double reduceStocks(@RequestBody OrderRequestDTO orderRequestDTO);



}
