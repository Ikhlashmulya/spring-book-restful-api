package com.github.ikhlashmulya.springrestapi.repository;

import com.github.ikhlashmulya.springrestapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
