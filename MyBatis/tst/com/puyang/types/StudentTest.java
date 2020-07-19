package com.puyang.types;

import org.junit.jupiter.api.Test;

public class StudentTest extends BaseTestCase {
    @Test
    public void shouldSerializeAndDeserialize() {
        Student student = Student.builder()
                .id(1)
                .name("name")
                .age(2)
                .email("email")
                .build();
        verify(student);
    }
}