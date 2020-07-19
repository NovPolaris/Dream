package com.puyang.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @NonNull
    private String username;
    @NonNull
    @Builder.Default
    private Integer totalCount = 0;
    @NonNull
    @Builder.Default
    private BigDecimal totalPrice = BigDecimal.ZERO;

    private List<CartItem> items;
}
