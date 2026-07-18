package com.codingshuttle.ecommerce.order_service.repository;

import com.codingshuttle.ecommerce.order_service.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersEntity,Long>
{

}
