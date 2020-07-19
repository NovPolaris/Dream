package com.puyang.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

class OrderItemTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() throws JsonProcessingException {
        OrderItem orderItem = OrderItem.builder()
                .name("name")
                .orderId("orderId")
                .count(1)
                .price(BigDecimal.TEN)
                .totalPrice(BigDecimal.TEN)
                .id(1)
                .build();
        verify(orderItem);
    }

    @Test
    public void shouldThrowNullPointerException() {
        Exception expected = assertThrows(NullPointerException.class, () -> {
            OrderItem orderItem = OrderItem.builder()
                    .name("name")
                    .orderId("orderId")
                    .count(1)
                    .id(1)
                    .build();
            verify(orderItem);
        });

        assertNotNull(expected);
    }
}