package com.example.order.mq;

import com.example.order.data.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderDto send(String topic, OrderDto orderDto) {
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
                log.info("kafka producer send data from order micro service {}, offset {}", orderDto, result.getRecordMetadata().offset());
            } else {
                log.info("unable to send message {}", ex.getMessage());
            }
        });
        return orderDto;
    }
}
