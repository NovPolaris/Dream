package com.puyang.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @EqualsAndHashCode.Exclude
    private BigDecimal price;

    @NonNull
    private String orderId;
    @NonNull
    private OrderStatus status;
    @NonNull
    private Integer userId;
    @NonNull
    private LocalDateTime createTime;

    @EqualsAndHashCode.Include
    private BigDecimal equalsPrice() {
        return price != null ? price.stripTrailingZeros() : null;
    }
}
