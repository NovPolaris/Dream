package com.puyang.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @NonNull
    private String name;
    @NonNull
    private String orderId;
    @NonNull
    private Integer count;
    @NonNull
    private BigDecimal price;
    @NonNull
    private BigDecimal totalPrice;

    private Integer id;
}
