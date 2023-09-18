package com.example.order.mapper;

import com.example.order.data.OrderDto;
import com.example.order.data.RequestOrder;
import com.example.order.data.ResponseOrder;
import com.example.order.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    OrderDto requestToDto(RequestOrder order);
}
