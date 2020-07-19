package com.puyang.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class CartItemTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() throws JsonProcessingException {
        CartItem cartItem = CartItem.builder()
                .sku("1")
                .name("name")
                .count(2)
                .price(BigDecimal.TEN)
                .totalPrice(BigDecimal.valueOf(20))
                .build();
        verify(cartItem);
    }

    @Test
    public void shouldThrowNullPointerException() {
        Exception expected = assertThrows(NullPointerException.class, () -> {
            CartItem cartItem = CartItem.builder()
                    .sku("1")
                    .name("name")
                    .count(2)
                    .totalPrice(BigDecimal.valueOf(20))
                    .build();
            verify(cartItem);
        });

        assertNotNull(expected);
    }
}
