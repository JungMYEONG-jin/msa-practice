package com.example.catalogs.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
