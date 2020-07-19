package com.puyang.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.net.URI;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;

    @NonNull
    private Integer pageNumber;
    @NonNull
    private Integer pageTotal;
    @NonNull
    private Long count;
    @NonNull
    private List<T> items;
    @Builder.Default
    private Integer pageSize = PAGE_SIZE;

    private URI uri;
}
