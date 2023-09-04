package com.github.ikhlashmulya.springrestapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ikhlashmulya.springrestapi.entity.Book;
import com.github.ikhlashmulya.springrestapi.model.*;
import com.github.ikhlashmulya.springrestapi.repository.BookRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();

        Book book1 = new Book();
        book1.setName("Buku Testing");
        book1.setAuthor("John Doe");
        book1.setSummary("this is summary");
        book1.setPublisher("Informatika");
        book1.setReadPage(2);
        book1.setTotalPage(100);
        book1.setFinished(false);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setName("Buku Testing 2");
        book2.setAuthor("John Doe");
        book2.setSummary("this is summary");
        book2.setPublisher("Informatika");
        book2.setReadPage(2);
        book2.setTotalPage(100);
        book2.setFinished(false);
        bookRepository.save(book2);
    }

    @Test
    void testCreateBookControllerSuccess() throws Exception {
        CreateBookRequest request = new CreateBookRequest();
        request.setName("Buku Java");
        request.setAuthor("John Doe");
        request.setSummary("this is summary");
        request.setPublisher("Informatika");
        request.setReadPage(2);
        request.setTotalPage(100);

        mockMvc.perform(
                post("/api/books")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isCreated()
        ).andDo(result -> {
            WebResponse<BookResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("success", response.getStatus());
            assertEquals(request.getName(), response.getData().getName());
            assertEquals(request.getAuthor(), response.getData().getAuthor());
            assertEquals(request.getSummary(), response.getData().getSummary());
            assertEquals(request.getTotalPage(), response.getData().getTotalPage());
            assertEquals(request.getReadPage(), response.getData().getReadPage());
            assertNotNull(response.getData().getId());
            assertNotNull(response.getData().getFinished());
            assertNotNull(response.getData().getInsertedAt());
            assertNotNull(response.getData().getUpdatedAt());
        });
    }

    @Test
    void testCreateBookControllerErrorReadPageNotExceedPageCountValidation() throws Exception {
        CreateBookRequest request = new CreateBookRequest();
        request.setName("Buku Java");
        request.setAuthor("John Doe");
        request.setSummary("this is summary");
        request.setPublisher("Informatika");
        request.setReadPage(200);
        request.setTotalPage(100);

        mockMvc.perform(
                post("/api/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<?> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("failed", response.getStatus());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testCreateBookControllerErrorNotBlankValidation() throws Exception {
        CreateBookRequest request = new CreateBookRequest();
        request.setName("");
        request.setAuthor("John Doe");
        request.setSummary("this is summary");
        request.setPublisher("Informatika");
        request.setReadPage(20);
        request.setTotalPage(100);

        mockMvc.perform(
                post("/api/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<?> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("failed", response.getStatus());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testUpdateBookControllerSuccess() throws Exception {
        Book book = new Book();
        book.setName("Buku Golang");
        book.setAuthor("John Doe");
        book.setSummary("this is summary");
        book.setPublisher("Informatika");
        book.setReadPage(2);
        book.setTotalPage(100);
        book.setFinished(false);
        bookRepository.save(book);

        UpdateBookRequest request = new UpdateBookRequest();
        request.setName("Buku Golang");
        request.setAuthor("John Doe");
        request.setSummary("this is summary");
        request.setPublisher("Informatika");
        request.setReadPage(100);
        request.setTotalPage(100);

        mockMvc.perform(
                put("/api/books/"+book.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<BookResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("success", response.getStatus());
            assertNull(response.getErrors());
            assertEquals(request.getReadPage(), response.getData().getReadPage());
            assertTrue(response.getData().getFinished());
        });
    }

    @Test
    void testUpdateBookControllerErrorReadPageNotExceedPageCountValidation() throws Exception {
        Book book = new Book();
        book.setId("10");
        book.setName("Buku Golang");
        book.setAuthor("John Doe");
        book.setSummary("this is summary");
        book.setPublisher("Informatika");
        book.setReadPage(2);
        book.setTotalPage(100);
        book.setFinished(false);
        bookRepository.save(book);

        UpdateBookRequest request = new UpdateBookRequest();
        request.setName("Buku Golang");
        request.setAuthor("John Doe");
        request.setSummary("this is summary");
        request.setPublisher("Informatika");
        request.setReadPage(120);
        request.setTotalPage(100);

        mockMvc.perform(
                put("/api/books/10")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<?> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("failed", response.getStatus());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testUpdateBookControllerErrorNotBlankValidation() throws Exception {
        Book book = new Book();
        book.setName("Buku Golang");
        book.setAuthor("John Doe");
        book.setSummary("this is summary");
        book.setPublisher("Informatika");
        book.setReadPage(2);
        book.setTotalPage(100);
        book.setFinished(false);
        bookRepository.save(book);

        UpdateBookRequest request = new UpdateBookRequest();
        request.setName("Buku Golang");
        request.setAuthor("");
        request.setSummary("this is summary");
        request.setPublisher("Informatika");
        request.setReadPage(10);
        request.setTotalPage(100);

        mockMvc.perform(
                put("/api/books/10")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<?> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("failed", response.getStatus());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testUpdateBookControllerErrorNotFound() throws Exception {
        UpdateBookRequest request = new UpdateBookRequest();
        request.setName("Buku Golang");
        request.setAuthor("John Doe");
        request.setSummary("this is summary");
        request.setPublisher("Informatika");
        request.setReadPage(10);
        request.setTotalPage(100);

        mockMvc.perform(
                put("/api/books/10")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<?> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("failed", response.getStatus());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testFindByIdBookControllerSuccess() throws Exception {
        Book book = new Book();
        book.setName("Buku Golang");
        book.setAuthor("John Doe");
        book.setSummary("this is summary");
        book.setPublisher("Informatika");
        book.setReadPage(2);
        book.setTotalPage(100);
        book.setFinished(false);
        bookRepository.save(book);

        mockMvc.perform(
                get("/api/books/"+book.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<BookResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("success", response.getStatus());
            assertEquals(book.getName(), response.getData().getName());
        });
    }

    @Test
    void testFindByIdBookControllerErrorNotFound() throws Exception {
        mockMvc.perform(
                get("/api/books/100")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<?> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("failed", response.getStatus());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testDeleteByIdBookControllerSuccess() throws Exception {
        Book book = new Book();
        book.setName("Buku Golang");
        book.setAuthor("John Doe");
        book.setSummary("this is summary");
        book.setPublisher("Informatika");
        book.setReadPage(2);
        book.setTotalPage(100);
        book.setFinished(false);
        bookRepository.save(book);

        mockMvc.perform(
                delete("/api/books/"+book.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("success", response.getStatus());
            assertNull(response.getErrors());
        });
    }

    @Test
    void testDeleteByIdBookControllerErrorNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/books/16")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("failed", response.getStatus());
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testFindAllBookControllerSuccess() throws Exception {
        mockMvc.perform(
                get("/api/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ListBookResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("success", response.getStatus());
            assertNull(response.getErrors());
            assertEquals(2, response.getData().size());
        });
    }
}
