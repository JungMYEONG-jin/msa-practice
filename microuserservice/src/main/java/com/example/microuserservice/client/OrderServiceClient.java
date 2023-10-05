package com.example.microuserservice.client;

import com.example.microuserservice.data.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceClient {
    @GetMapping("/order-service/{userId}/order")
    List<ResponseOrder> getOrders(@PathVariable(name = "userId") String userId);
}
