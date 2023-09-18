package com.example.order.port.out;

import com.example.order.domain.Order;

import java.util.List;

public interface OrderFindPort {
    List<Order> findAll();
    Order findByOrderId(String orderId);
    List<Order> findByUserId(String userId);
}
