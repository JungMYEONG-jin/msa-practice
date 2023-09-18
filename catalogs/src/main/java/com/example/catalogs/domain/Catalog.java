package com.example.catalogs.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Catalog {
    private Long id;
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
