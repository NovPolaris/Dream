package com.puyang.types;

import com.google.gson.Gson;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTestCase {
    private Gson gson = new Gson();

    protected <T> void verify(T obj) {
        String jsonString = gson.toJson(obj);
        Object actual = gson.fromJson(jsonString, obj.getClass());
        assertEquals(obj, actual);
    }
}
