package com.example.catalogs.mq;

import com.example.catalogs.domain.Catalog;
import com.example.catalogs.port.out.CatalogFindPort;
import com.example.catalogs.port.out.CatalogSavePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CatalogFindPort catalogFindPort;
    private final CatalogSavePort catalogSavePort;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String message) {
        log.info("message {}", message);
        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper objectMapper = new ObjectMapper();
        try{
            map = objectMapper.readValue(message, new TypeReference<Map<Object, Object>>() {
            });
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Catalog catalog = catalogFindPort.findByProductId((String) map.get("productId"));
        if (catalog != null) {
            catalog.setStock(catalog.getStock() - (Integer) map.get("qty"));
            catalogSavePort.save(catalog);
        }
    }
}
