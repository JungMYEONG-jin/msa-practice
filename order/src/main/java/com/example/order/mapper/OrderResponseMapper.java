package com.example.order.mapper;

import com.example.order.data.OrderDto;
import com.example.order.data.ResponseOrder;
import com.example.order.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    ResponseOrder domainToResponse(Order order);
    ResponseOrder dtoToResponse(OrderDto order);
}
