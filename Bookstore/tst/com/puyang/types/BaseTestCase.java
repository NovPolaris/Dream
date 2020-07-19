package com.puyang.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;

public class BaseTestCase {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected <T> void verify(T obj) throws JsonProcessingException {
        String jsonString = OBJECT_MAPPER.writeValueAsString(obj);
        Object actual = OBJECT_MAPPER.readValue(jsonString, obj.getClass());
        assertEquals(obj, actual);
    }
}
