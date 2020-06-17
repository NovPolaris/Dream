package com.puyang.test;

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
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Address address;
}
