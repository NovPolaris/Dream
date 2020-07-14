package com.puyang.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

public class CartTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() throws JsonProcessingException {
        Cart cart = Cart.builder()
                .username("username")
                .totalCount(2)
                .totalPrice(BigDecimal.valueOf(20))
                .items(Collections.emptyList())
                .build();
        verify(cart);
    }
}
