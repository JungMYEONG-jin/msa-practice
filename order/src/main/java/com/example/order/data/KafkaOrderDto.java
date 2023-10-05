package com.example.order.data;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class KafkaOrderDto implements Serializable {
    private Schema schema;
    private Payload payload;
}
