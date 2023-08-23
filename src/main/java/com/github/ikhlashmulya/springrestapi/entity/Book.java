package com.github.ikhlashmulya.springrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Book {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    private String summary;

    @Column(nullable = false)
    private String publisher;

    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @Column(name = "read_page", nullable = false)
    private Integer readPage;

    @Column(nullable = false)
    private Boolean finished;

    @Column(name = "inserted_at", nullable = false)
    private Timestamp insertedAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
