package com.codingshuttle.ecommerce.order_service.service;

import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDTO;
import com.codingshuttle.ecommerce.order_service.dto.OrdersDTO;
import com.codingshuttle.ecommerce.order_service.entity.OrdersEntity;
import com.codingshuttle.ecommerce.order_service.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService
{
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    public List<OrderRequestDTO> getAllOrders()
    {
        log.info("getAllOrders()");

        List<OrdersEntity> allOrders = ordersRepository.findAll();

      return  allOrders.stream()
                .map(order -> modelMapper.map(order, OrderRequestDTO.class))
                .toList();
    }

    public OrderRequestDTO getOrderById(Long id)
    {
        log.info("getOrderById()");

        OrdersEntity ordersEntity = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No orders found with that id"));

        return modelMapper.map(ordersEntity, OrderRequestDTO.class);
    }

}
