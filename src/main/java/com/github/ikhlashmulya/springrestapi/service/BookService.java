package com.github.ikhlashmulya.springrestapi.service;

import com.github.ikhlashmulya.springrestapi.entity.Book;
import com.github.ikhlashmulya.springrestapi.exception.ResourceNotFoundException;
import com.github.ikhlashmulya.springrestapi.model.BookResponse;
import com.github.ikhlashmulya.springrestapi.model.CreateBookRequest;
import com.github.ikhlashmulya.springrestapi.model.ListBookResponse;
import com.github.ikhlashmulya.springrestapi.model.UpdateBookRequest;
import com.github.ikhlashmulya.springrestapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ValidationService validationService;

    public BookResponse findById(String id) {
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return toBookResponse(book);
    }

    public BookResponse Create(CreateBookRequest request) {
        validationService.validate(request);

        Book book = Book.builder().
                name(request.getName()).
                author(request.getAuthor()).
                publisher(request.getPublisher()).
                readPage(request.getReadPage()).
                totalPage(request.getTotalPage()).
                summary(request.getSummary()).
                finished(request.getTotalPage().equals(request.getReadPage())).
                build();

        bookRepository.save(book);

        return toBookResponse(book);
    }

    public BookResponse update(UpdateBookRequest request) {
        validationService.validate(request);

        Book book = bookRepository.findById(request.getId()).
                orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setName(request.getName());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setSummary(request.getSummary());
        book.setTotalPage(request.getTotalPage());
        book.setReadPage(request.getReadPage());
        book.setFinished(request.getTotalPage().equals(request.getReadPage()));

        bookRepository.save(book);

        return toBookResponse(book);
    }

    public void deleteById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookRepository.delete(book);
    }

    public Page<ListBookResponse> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);

        return books.map(this::toListBookResponse);
    }

    private BookResponse toBookResponse(Book book) {
        return BookResponse.builder().
                id(book.getId()).
                name(book.getName()).
                author(book.getAuthor()).
                summary(book.getSummary()).
                publisher(book.getPublisher()).
                totalPage(book.getTotalPage()).
                readPage(book.getReadPage()).
                finished(book.getFinished()).
                insertedAt(book.getInsertedAt()).
                updatedAt(book.getUpdatedAt()).build();
    }

    private ListBookResponse toListBookResponse(Book book) {
        return ListBookResponse.builder().id(book.getId()).name(book.getName()).publisher(book.getPublisher()).build();
    }
}
