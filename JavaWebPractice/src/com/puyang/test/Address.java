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
public class Address {
    @NonNull
    private String city;
    @NonNull
    private String province;
    @NonNull
    private String country;
}
