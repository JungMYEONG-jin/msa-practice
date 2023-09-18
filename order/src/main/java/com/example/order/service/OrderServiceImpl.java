package com.example.order.service;

import com.example.order.data.OrderDto;
import com.example.order.domain.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.port.in.OrderService;
import com.example.order.port.out.OrderFindPort;
import com.example.order.port.out.OrderSavePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderFindPort orderFindPort;
    private final OrderSavePort orderSavePort;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());
        // dto to domain
        Order order = orderMapper.dtoToDomain(orderDto);
        Order savedOrder = orderSavePort.save(order);
        return orderMapper.domainToDto(savedOrder);
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        Order order = orderFindPort.findByOrderId(orderId);
        return orderMapper.domainToDto(order);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(String userId) {
        List<Order> orders = orderFindPort.findByUserId(userId);
        return orders.stream().map(it -> orderMapper.domainToDto(it)).collect(Collectors.toList());
    }
}
