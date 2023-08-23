package com.github.ikhlashmulya.springrestapi.model;

import lombok.*;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class WebResponse<T> {
    private String status;
    private String errors;
    private T data;
}
