package com.puyang.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @NonNull
    private String name;
    @NonNull
    private String author;
    @NonNull
    private Double price;
    @NonNull
    private Integer sales;
    @NonNull
    private Integer stock;
    @Builder.Default
    private String imgPath = "static/img/default.jpg";
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Integer id;
}
