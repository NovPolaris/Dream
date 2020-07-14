package com.puyang.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class BookTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() throws JsonProcessingException {
        Book book = Book.builder()
                .id(1)
                .stock(2)
                .sales(3)
                .price(4.0)
                .author("author")
                .name("name")
                .imgPath("imgPath")
                .build();
        verify(book);
    }

    @Test
    public void shouldThrowNullPointerException() {
        Exception expected = assertThrows(NullPointerException.class, () -> {
            Book book = Book.builder()
                    .id(1)
                    .stock(2)
                    .sales(3)
                    .price(4.0)
                    .name("name")
                    .imgPath("imgPath")
                    .build();
            verify(book);
        });

        assertNotNull(expected);
    }
}