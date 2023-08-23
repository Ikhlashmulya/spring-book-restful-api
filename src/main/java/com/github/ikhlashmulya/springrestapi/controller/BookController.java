package com.github.ikhlashmulya.springrestapi.controller;

import com.github.ikhlashmulya.springrestapi.model.*;
import com.github.ikhlashmulya.springrestapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<BookResponse>> findById(@PathVariable("id") String id) {

        BookResponse response = bookService.findById(id);

        return ResponseEntity.ok(WebResponse.<BookResponse>builder().
                status("success").
                data(response).
                build());
    }

    @PostMapping(value = "/books",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<BookResponse>> create(@RequestBody CreateBookRequest request) {

        BookResponse response = bookService.Create(request);

        return new ResponseEntity<>(WebResponse.<BookResponse>builder().
                status("success").
                data(response).
                build(),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/books/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<BookResponse>> update(
            @RequestBody UpdateBookRequest request,
            @PathVariable("id") String id) {

        request.setId(id);
        BookResponse response = bookService.update(request);

        return ResponseEntity.ok(WebResponse.<BookResponse>builder().
                status("success").
                data(response).build());
    }

    @DeleteMapping(value = "/books/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> deleteById(@PathVariable("id") String id) {
        bookService.deleteById(id);

        return ResponseEntity.ok(WebResponse.<String>builder().status("success").data("data deleted").build());
    }

    @GetMapping("/books")
    public ResponseEntity<WebResponse<List<ListBookResponse>>> findAll() {
        List<ListBookResponse> responses = bookService.findAll();

        return ResponseEntity.ok(WebResponse.<List<ListBookResponse>>builder().status("success").data(responses).build());
    }
}
