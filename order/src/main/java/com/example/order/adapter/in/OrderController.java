package com.example.order.adapter.in;

import com.example.order.data.OrderDto;
import com.example.order.data.RequestOrder;
import com.example.order.data.ResponseOrder;
import com.example.order.mapper.OrderRequestMapper;
import com.example.order.mapper.OrderResponseMapper;
import com.example.order.port.in.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Environment environment;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderRequestMapper orderRequestMapper;

    @GetMapping("/health-check")
    public String healthCheck(HttpServletRequest request) {
        return String.format("Working my port is %s", request.getServerPort());
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder requestOrder) {
        OrderDto dto = orderRequestMapper.requestToDto(requestOrder);
        dto.setUserId(userId);
        OrderDto order = orderService.createOrder(dto);
        ResponseOrder response = orderResponseMapper.dtoToResponse(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity getOrders(@PathVariable("userId") String userId) {
        List<ResponseOrder> response = orderService.getOrdersByUserId(userId).stream().map(it -> orderResponseMapper.dtoToResponse(it)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
