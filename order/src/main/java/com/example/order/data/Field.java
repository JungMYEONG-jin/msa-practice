package com.example.order.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Field {
    private String type;
    private boolean optional;
    private String field;
}
