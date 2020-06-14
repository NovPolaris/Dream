package com.puyang.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    @Builder.Default
    private String email = "";

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private int id;
}
