package com.example.order.adapter.out.persistence;

import com.example.order.domain.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.port.out.OrderFindPort;
import com.example.order.port.out.OrderSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderAdapter implements OrderFindPort, OrderSavePort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public Order findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId).map(orderMapper::entityToDomain).orElse(null);
    }

    @Override
    public List<Order> findByUserId(String userId) {
        return orderRepository.findByUserId(userId).stream().map(orderMapper::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public Order save(Order order) {
        return orderMapper.entityToDomain(orderRepository.save(orderMapper.domainToEntity(order)));
    }
}
