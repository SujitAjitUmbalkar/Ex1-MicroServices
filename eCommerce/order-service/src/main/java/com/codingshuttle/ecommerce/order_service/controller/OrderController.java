package com.codingshuttle.ecommerce.order_service.controller;
import com.codingshuttle.ecommerce.order_service.dto.OrdersDTO;
import com.codingshuttle.ecommerce.order_service.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController
{
    private final OrderService orderService;

    @GetMapping("/hello")
    public String dummyApi()
    {
        return "Hello Dummy from Order Service  ! ";
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders(HttpServletRequest httpServletRequest)
    {
        log.info("Fetching all orders via controller");
        List<OrdersDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders); // Returns 200 OK with the list of orders
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Long id)
    {
        log.info("Fetching order with ID: {} via controller", id);
        OrdersDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order); // Returns 200 OK with the order
    }
}