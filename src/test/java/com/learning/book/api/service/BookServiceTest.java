package com.learning.book.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learning.book.api.dto.BookRequest;
import com.learning.book.api.dto.BookResponse;
import com.learning.book.api.entity.Book;
import com.learning.book.api.exception.ResourceNotFoundException;
import com.learning.book.api.mapper.BookMapper;
import com.learning.book.api.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

  @Mock private BookRepository bookRepository;

  @Mock private BookMapper bookMapper;

  @InjectMocks private BookService bookService;

  private BookRequest bookRequest;
  private Book book;
  private BookResponse bookResponse;
  private static final int DEFAULT_BOOK_PRICE = 30;

  @BeforeEach
  void setUp() {
    bookRequest = new BookRequest();
    bookRequest.setTitle("Test Book");
    bookRequest.setAuthor("Test Author");
    bookRequest.setIsbn("1234567890");
    bookRequest.setPrice(DEFAULT_BOOK_PRICE);

    book = new Book();
    book.setId(1L);
    book.setTitle("Test Book");
    book.setAuthor("Test Author");
    book.setIsbn("1234567890");
    book.setPrice(DEFAULT_BOOK_PRICE);

    bookResponse = new BookResponse();
    bookResponse.setId(1L);
    bookResponse.setTitle("Test Book");
    bookResponse.setAuthor("Test Author");
    bookResponse.setIsbn("1234567890");
    bookResponse.setPrice(DEFAULT_BOOK_PRICE);
  }

  @Test
  void createBook() {
    when(bookMapper.toEntity(bookRequest)).thenReturn(book);
    when(bookRepository.save(book)).thenReturn(book);
    when(bookMapper.toResponse(book)).thenReturn(bookResponse);

    BookResponse result = bookService.createBook(bookRequest);

    assertEquals(bookResponse, result);
    verify(bookRepository).save(book);
  }

  @Test
  void getBook() {
    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(bookMapper.toResponse(book)).thenReturn(bookResponse);

    BookResponse result = bookService.getBook(1L);

    assertEquals(bookResponse, result);
  }

  @Test
  void getBookNotFound() {
    when(bookRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> bookService.getBook(1L));
  }

  @Test
  void getAllBooks() {
    when(bookRepository.findAll()).thenReturn(List.of(book));
    when(bookMapper.toResponse(book)).thenReturn(bookResponse);

    List<BookResponse> result = bookService.getAllBooks();

    assertEquals(1, result.size());
    assertEquals(bookResponse, result.getFirst());
  }
}
