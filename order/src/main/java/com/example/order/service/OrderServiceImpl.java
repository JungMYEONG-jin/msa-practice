package com.example.order.service;

import com.example.order.data.OrderDto;
import com.example.order.port.in.OrderService;
import com.example.order.port.out.OrderFindPort;
import com.example.order.port.out.OrderSavePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderFindPort orderFindPort;
    private final OrderSavePort orderSavePort;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());
        // save
        return null;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(String userId) {
        return null;
    }
}
