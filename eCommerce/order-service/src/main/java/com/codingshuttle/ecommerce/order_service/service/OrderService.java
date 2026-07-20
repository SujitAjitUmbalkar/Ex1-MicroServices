package com.codingshuttle.ecommerce.order_service.service;

import com.codingshuttle.ecommerce.order_service.clients.InventoryOpenFeignClient;
import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDTO;
import com.codingshuttle.ecommerce.order_service.entity.OrderItemEntity;
import com.codingshuttle.ecommerce.order_service.entity.OrderStatus;
import com.codingshuttle.ecommerce.order_service.entity.OrdersEntity;
import com.codingshuttle.ecommerce.order_service.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService
{
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;
    private final InventoryOpenFeignClient  inventoryOpenFeignClient;

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

    public OrderRequestDTO createOrder(OrderRequestDTO order)
    {
        log.info("createOrder()");

//        reduce stock & calculate price
        Double totalPrice = inventoryOpenFeignClient.reduceStocks(order);

//        Entity conversion
        OrdersEntity ordersEntity = modelMapper.map(order, OrdersEntity.class);

//   for each item , set single order
        for(OrderItemEntity orderItemEntity : ordersEntity.getItems())
        {
            orderItemEntity.setOrder(ordersEntity);
        }

//      set orders price and status
        ordersEntity.setTotalPrice(totalPrice);
        ordersEntity.setOrderStatus(OrderStatus.CONFIRMED);

//        save entity
        OrdersEntity savedOrder =  ordersRepository.save(ordersEntity);

//        return order
        return modelMapper.map(savedOrder, OrderRequestDTO.class);
    }
}
