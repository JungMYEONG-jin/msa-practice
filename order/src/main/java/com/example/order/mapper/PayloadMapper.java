package com.example.order.mapper;

import com.example.order.data.OrderDto;
import com.example.order.data.Payload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PayloadMapper {
    @Mapping(source = "orderId", target = "order_id")
    @Mapping(source = "userId", target = "user_id")
    @Mapping(source = "productId", target = "product_id")
    @Mapping(source = "totalPrice", target = "total_price")
    Payload dtoToPayload(OrderDto orderDto);
}
