package com.puyang.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

class OrderTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() throws JsonProcessingException {
        Order order = Order.builder()
                .orderId("orderId")
                .createTime(LocalDateTime.now())
                .price(BigDecimal.ONE)
                .status(OrderStatus.未付款)
                .userId(1)
                .build();
        verify(order);
    }

    @Test
    public void shouldThrowNullPointerException() {
        Exception expected = assertThrows(NullPointerException.class, () -> {
            Order order = Order.builder()
                    .orderId("orderId")
                    .createTime(LocalDateTime.now())
                    .price(BigDecimal.ONE)
                    .userId(1)
                    .build();
            verify(order);
        });

        assertNotNull(expected);
    }
}