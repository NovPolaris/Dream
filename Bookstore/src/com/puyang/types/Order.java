package com.puyang.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @NonNull
    private String orderId;
    @NonNull
    private LocalDateTime createTime;
    @NonNull
    private BigDecimal price;
    @NonNull
    private OrderStatus status;
    @NonNull
    private Integer userId;
}
