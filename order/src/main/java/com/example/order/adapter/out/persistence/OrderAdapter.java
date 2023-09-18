package com.example.order.adapter.out.persistence;

import com.example.order.data.OrderDto;
import com.example.order.domain.Order;
import com.example.order.port.out.OrderFindPort;
import com.example.order.port.out.OrderSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderAdapter implements OrderFindPort, OrderSavePort {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findByOrderId(String orderId) {
        return null;
    }

    @Override
    public List<Order> findByUserId(String userId) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Order save(OrderDto orderDto) {
        return null;
    }
}
