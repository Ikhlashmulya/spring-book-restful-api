package com.github.ikhlashmulya.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Data @Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class BookResponse {
    private String id;
    private String name;
    private String author;
    private String summary;
    private String publisher;

    @JsonProperty(value = "page_count")
    private Integer pageCount;

    @JsonProperty(value = "read_page")
    private Integer readPage;

    private Boolean finished;

    @JsonProperty(value = "inserted_at")
    private Timestamp insertedAt;

    @JsonProperty(value = "updated_at")
    private Timestamp updatedAt;
}
