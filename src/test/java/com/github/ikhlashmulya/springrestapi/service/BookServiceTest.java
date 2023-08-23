package com.github.ikhlashmulya.springrestapi.service;


import com.github.ikhlashmulya.springrestapi.entity.Book;
import com.github.ikhlashmulya.springrestapi.exception.ResourceNotFoundException;
import com.github.ikhlashmulya.springrestapi.model.BookResponse;
import com.github.ikhlashmulya.springrestapi.model.CreateBookRequest;
import com.github.ikhlashmulya.springrestapi.model.ListBookResponse;
import com.github.ikhlashmulya.springrestapi.model.UpdateBookRequest;
import com.github.ikhlashmulya.springrestapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private ValidationService validationService;
    
    @InjectMocks
    private BookService bookService;

    @Test
    void testFindByIdBookServiceSuccess() {
        when(bookRepository.findById("1")).thenReturn(Optional.of(Book.builder().
                id("1").
                name("test buku").
                author("John Doe").
                publisher("publisher").
                readPage(1).
                pageCount(100).
                finished(false).
                insertedAt(new Timestamp(System.currentTimeMillis())).
                updatedAt(new Timestamp(System.currentTimeMillis())).
                build()));

        BookResponse bookResponse = bookService.findById("1");

        assertNotNull(bookResponse);
        assertEquals("John Doe", bookResponse.getAuthor());
    }

    @Test
    void testFindByIdBookServiceFailNotFound() {
        when(bookRepository.findById("2")).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.findById("2");
        });
    }

    @Test
    void testCreateBookServiceSuccess() {
        BookResponse response = bookService.Create(CreateBookRequest.builder().
                name("test buku").
                author("John Doe").
                publisher("publisher").
                readPage(1).
                pageCount(100).
                build());
        assertNotNull(response.getId());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testUpdateBookServiceSuccess() {

        when(bookRepository.findById("1")).thenReturn(Optional.of(Book.builder().
                id("1").
                name("test buku").
                author("John Doe").
                publisher("publisher").
                readPage(1).
                pageCount(100).
                finished(false).
                insertedAt(new Timestamp(System.currentTimeMillis())).
                updatedAt(new Timestamp(System.currentTimeMillis())).
                build()));

        BookResponse response = bookService.update(UpdateBookRequest.builder().
                id("1").
                name("test buku").
                author("John Doe").
                publisher("publisher").
                readPage(1).
                pageCount(100).build());
        assertNotNull(response);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testUpdateBookServiceFailedNotFound() {
        when(bookRepository.findById("2")).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.update(UpdateBookRequest.builder().id("2").build());
        });
    }

    @Test
    void testDeleteByIdBookServiceSuccess() {
        Book book = Book.builder().
                id("1").
                name("test buku").
                author("John Doe").
                publisher("publisher").
                readPage(1).
                pageCount(100).
                finished(false).
                insertedAt(new Timestamp(System.currentTimeMillis())).
                updatedAt(new Timestamp(System.currentTimeMillis())).
                build();

        when(bookRepository.findById("1")).thenReturn(Optional.of(book));

        bookService.deleteById("1");

        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testDeleteByIdBookServiceErrorNotFound() {
        when(bookRepository.findById("2")).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteById("2");
        });
    }

    @Test
    void testFindAllBookServiceSuccess() {
        List<Book> bookList = List.of(Book.builder().
                id("1").
                name("test buku").
                author("John Doe").
                publisher("publisher").
                readPage(1).
                pageCount(100).
                finished(false).
                insertedAt(new Timestamp(System.currentTimeMillis())).
                updatedAt(new Timestamp(System.currentTimeMillis())).
                build(), Book.builder().
                id("1").
                name("test buku").
                author("John Doe").
                publisher("publisher").
                readPage(1).
                pageCount(100).
                finished(false).
                insertedAt(new Timestamp(System.currentTimeMillis())).
                updatedAt(new Timestamp(System.currentTimeMillis())).
                build());

        when(bookRepository.findAll()).thenReturn(bookList);

        List<ListBookResponse> responses = bookService.findAll();
        assertNotNull(responses);
        assertEquals(2, responses.size());
    }
}
