package com.example.order.mq;

import com.example.order.data.*;
import com.example.order.domain.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.mapper.PayloadMapper;
import com.example.order.port.out.OrderSavePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final PayloadMapper payloadMapper;
    private final OrderMapper orderMapper;
    private final OrderSavePort orderSavePort;

    private List<Field> fields = Arrays.asList(
            new Field("string", true, "order_id"),
            new Field("string", true, "user_id"),
            new Field("string", true, "product_id"),
            new Field("int32", true, "qty"),
            new Field("int32", true, "unit_price"),
            new Field("int32", true, "total_price")
            );
    private Schema schema = Schema.builder().type("struct").name("orders").optional(false).fields(fields).build();

    public OrderDto send(String topic, OrderDto orderDto) {
        // create payload
        Payload payload = payloadMapper.dtoToPayload(orderDto);
        // kafka dto
        KafkaOrderDto kafkaOrderDto = KafkaOrderDto.builder().payload(payload).schema(schema).build();

        String json = "";
//        ObjectMapper objectMapper = new ObjectMapper();
        try{
            json = objectMapper.writeValueAsString(orderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, json);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("kafka producer send data from order micro service {}, offset {}", kafkaOrderDto, result.getRecordMetadata().offset());
                Order order = orderMapper.dtoToDomain(orderDto);
                orderSavePort.save(order);
            } else {
                log.info("unable to send message {}", ex.getMessage());
            }
        });
        return orderDto;
    }
}
