package com.learning.book.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.book.api.dto.BookRequest;
import com.learning.book.api.dto.BookResponse;
import com.learning.book.api.service.BookService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService bookService;

  @MockBean private MeterRegistry meterRegistry;

  @MockBean private Counter mockCounter;

  private BookRequest bookRequest;
  private BookResponse bookResponse;
  private static final int DEFAULT_BOOK_PRICE = 30;

  @BeforeEach
  void setUp() {

    when(meterRegistry.counter(anyString())).thenReturn(mockCounter);

    bookRequest = new BookRequest();
    bookRequest.setTitle("Test Book");
    bookRequest.setAuthor("Test Author");
    bookRequest.setIsbn("1234567890");
    bookRequest.setPrice(DEFAULT_BOOK_PRICE);

    bookResponse = new BookResponse();
    bookResponse.setId(1L);
    bookResponse.setTitle("Test Book");
    bookResponse.setAuthor("Test Author");
    bookResponse.setIsbn("1234567890");
    bookResponse.setPrice(DEFAULT_BOOK_PRICE);
  }

  @Test
  void createBook() throws Exception {
    Mockito.when(bookService.createBook(any())).thenReturn(bookResponse);

    mockMvc
        .perform(
            post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.title").value("Test Book"));
  }

  @Test
  void getBook() throws Exception {
    Mockito.when(bookService.getBook(1L)).thenReturn(bookResponse);

    mockMvc
        .perform(get("/api/v1/books/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Test Book"));
  }

  @Test
  void getAllBooks() throws Exception {
    Mockito.when(bookService.getAllBooks()).thenReturn(Collections.singletonList(bookResponse));

    mockMvc
        .perform(get("/api/v1/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title").value("Test Book"));
  }

  @Test
  void updateBook() throws Exception {
    Mockito.when(bookService.updateBook(Mockito.eq(1L), any())).thenReturn(bookResponse);

    mockMvc
        .perform(
            put("/api/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Test Book"));
  }

  @Test
  void deleteBook() throws Exception {
    mockMvc.perform(delete("/api/v1/books/1")).andExpect(status().isNoContent());
  }
}
