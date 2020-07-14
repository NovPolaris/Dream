package com.puyang.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class UserTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() throws JsonProcessingException {
        User user = User.builder()
                .id(1)
                .username("username")
                .email("email")
                .password("password")
                .build();
        verify(user);
    }

    @Test
    public void shouldThrowNullPointerException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            User user = User.builder()
                    .id(1)
                    .username("username")
                    .email("email")
                    .build();
            verify(user);
        });
        assertNotNull(exception);
    }
}
