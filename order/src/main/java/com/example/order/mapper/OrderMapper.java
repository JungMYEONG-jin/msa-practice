package com.example.order.mapper;

import com.example.order.adapter.out.persistence.OrderEntity;
import com.example.order.data.OrderDto;
import com.example.order.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto domainToDto(Order order);
    @Mapping(target = "id", ignore = true)
    Order dtoToDomain(OrderDto order);
    OrderEntity domainToEntity(Order order);
    Order entityToDomain(OrderEntity order);
}
