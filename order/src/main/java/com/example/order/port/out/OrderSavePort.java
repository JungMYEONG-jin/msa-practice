package com.example.order.port.out;

import com.example.order.data.OrderDto;
import com.example.order.domain.Order;

public interface OrderSavePort {
    Order save(Order order);
    Order save(OrderDto orderDto);
}
