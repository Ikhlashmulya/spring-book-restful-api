package com.github.ikhlashmulya.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Data @Builder
public class BookResponse {
    private String id;
    private String name;
    private String author;
    private String summary;
    private String publisher;

    @JsonProperty(value = "total_page")
    private Integer totalPage;

    @JsonProperty(value = "read_page")
    private Integer readPage;

    private Boolean finished;

    @JsonProperty(value = "inserted_at")
    private Instant insertedAt;

    @JsonProperty(value = "updated_at")
    private Instant updatedAt;
}
