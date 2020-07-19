package com.puyang.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.junit.Assert.assertEquals;

public class BaseTestCase {
    public static final ObjectMapper OBJECT_MAPPER = createMapper();

    protected <T> void verify(T obj) throws JsonProcessingException {
        String jsonString = OBJECT_MAPPER.writeValueAsString(obj);
        Object actual = OBJECT_MAPPER.readValue(jsonString, obj.getClass());
        assertEquals(obj, actual);
    }

    private static ObjectMapper createMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
