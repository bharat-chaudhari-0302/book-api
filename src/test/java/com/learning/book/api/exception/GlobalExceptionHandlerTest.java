package com.learning.book.api.exception;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.learning.book.api.controller.BookController;
import com.learning.book.api.service.BookService;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class GlobalExceptionHandlerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService bookService;

  @MockBean private MeterRegistry meterRegistry;

  private static final long WRONG_BOOK_ID = 99L;

  @Test
  void handleResourceNotFound() throws Exception {
    when(bookService.getBook(WRONG_BOOK_ID))
        .thenThrow(new ResourceNotFoundException("Book not found"));

    mockMvc
        .perform(get("/api/v1/books/99"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Book not found"));
  }
}
