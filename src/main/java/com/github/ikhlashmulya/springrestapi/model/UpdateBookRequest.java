package com.github.ikhlashmulya.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ikhlashmulya.springrestapi.util.annotation.ReadPageNotExceedPageCount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ReadPageNotExceedPageCount
public class UpdateBookRequest {
    @JsonIgnore
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    private String summary;

    @NotBlank
    private String publisher;

    @NotNull @JsonProperty(value = "page_count")
    private Integer pageCount;

    @NotNull @JsonProperty(value = "read_page")
    private Integer readPage;
}
