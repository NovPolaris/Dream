package com.puyang.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class PageTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() throws JsonProcessingException, URISyntaxException {
        Page<Book> page = Page.<Book>builder()
                .items(Collections.emptyList())
                .count(1L)
                .pageNumber(2)
                .pageSize(3)
                .pageTotal(4)
                .uri(new URI("uri"))
                .build();
        verify(page);
    }

    @Test
    public void shouldThrowNullPointerException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Page<Book> page = Page.<Book>builder()
                    .items(Collections.emptyList())
                    .count(1L)
                    .pageSize(3)
                    .pageTotal(4)
                    .build();
            verify(page);
        });
        assertNotNull(exception);
    }
}
