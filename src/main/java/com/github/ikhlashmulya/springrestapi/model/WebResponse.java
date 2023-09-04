package com.github.ikhlashmulya.springrestapi.model;

import lombok.*;

import java.util.Map;

@Data @Builder
public class WebResponse<T> {
    private String status;
    private String errors;
    private T data;
    private Map<String, ?> pagination;
}
