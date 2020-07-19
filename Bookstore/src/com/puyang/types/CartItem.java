package com.puyang.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @NonNull
    @EqualsAndHashCode.Exclude
    private BigDecimal price;
    @NonNull
    @EqualsAndHashCode.Exclude
    private BigDecimal totalPrice;

    @NonNull
    private String name;
    @NonNull
    private Integer count;

    private Integer bookId;

    @EqualsAndHashCode.Include
    private BigDecimal equalsPrice() {
        return price != null ? price.stripTrailingZeros() : null;
    }

    @EqualsAndHashCode.Include
    private BigDecimal equalsTotalPrice() {
        return totalPrice != null ? totalPrice.stripTrailingZeros() : null;
    }
}
