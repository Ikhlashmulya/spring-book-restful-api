package com.github.ikhlashmulya.springrestapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.Instant;

@Entity @Table
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    private String summary;

    @Column(nullable = false)
    private String publisher;

    @Column(name = "total_page", nullable = false)
    private Integer totalPage;

    @Column(name = "read_page", nullable = false)
    private Integer readPage;

    @Column(nullable = false)
    private Boolean finished;

    @CreatedDate
    @Column(name = "inserted_at")
    private Instant insertedAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
