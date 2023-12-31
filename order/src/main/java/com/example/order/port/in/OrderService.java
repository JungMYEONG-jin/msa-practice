package com.example.order.port.in;

import com.example.order.data.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    List<OrderDto> getOrdersByUserId(String userId);
}
