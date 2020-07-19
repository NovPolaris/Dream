package com.puyang.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @NonNull
    private Integer id;
    @NonNull
    private Integer age;
    @NonNull
    private String name;
    @NonNull
    private String email;
}
