package com.github.ikhlashmulya.springrestapi.model;

import lombok.*;

@Data @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListBookResponse {
    private String id;
    private String name;
    private String publisher;
}
