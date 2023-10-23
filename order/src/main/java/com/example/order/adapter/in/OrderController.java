package com.example.order.adapter.in;

import com.example.order.data.OrderDto;
import com.example.order.data.RequestOrder;
import com.example.order.data.ResponseOrder;
import com.example.order.domain.Order;
import com.example.order.mapper.OrderRequestMapper;
import com.example.order.mapper.OrderResponseMapper;
import com.example.order.mq.KafkaProducer;
import com.example.order.mq.OrderProducer;
import com.example.order.port.in.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Environment environment;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderRequestMapper orderRequestMapper;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

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
        log.info("before add order data");
        // jpa
        OrderDto orderDto = orderRequestMapper.requestToDto(requestOrder);
        orderDto.setUserId(userId);
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(requestOrder.getUnitPrice() * requestOrder.getQty());

        // kafka
        kafkaProducer.send("example-catalog-topic", orderDto);
        orderProducer.send("orders", orderDto);

        // rest api
        ResponseOrder response = orderResponseMapper.dtoToResponse(orderDto);
        log.info("after add order data");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity getOrders(@PathVariable("userId") String userId) throws Exception {
        log.info("before retrieve order data");
        List<ResponseOrder> response = orderService.getOrdersByUserId(userId).stream().map(orderResponseMapper::dtoToResponse).collect(Collectors.toList());

        try {
            Thread.sleep(1000);
            throw new Exception("error!!!");
        } catch (InterruptedException e) {
            log.error("error message {}", e.getMessage());
        }
        log.info("after retrieve order data");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
