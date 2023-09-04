package com.github.ikhlashmulya.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ikhlashmulya.springrestapi.util.annotation.ReadPageNotExceedPageCount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@ReadPageNotExceedPageCount
public class CreateBookRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String author;

    private String summary;

    @NotBlank
    private String publisher;

    @NotNull @JsonProperty(value = "total_page")
    private Integer totalPage;

    @NotNull @JsonProperty(value = "read_page")
    private Integer readPage;
}
