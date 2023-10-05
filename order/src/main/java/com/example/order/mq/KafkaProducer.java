package com.example.order.mq;

import com.example.order.data.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public OrderDto send(String topic, OrderDto orderDto) {
        String json = "";
        try{
            json = objectMapper.writeValueAsString(orderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, json);
        log.info("kafka producer send data from order micro service {}", orderDto);
        return orderDto;
    }
}
