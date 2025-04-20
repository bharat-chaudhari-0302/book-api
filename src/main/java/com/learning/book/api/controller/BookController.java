package com.learning.book.api.controller;


import com.learning.book.api.dto.BookRequest;
import com.learning.book.api.dto.BookResponse;
import com.learning.book.api.service.BookService;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final MeterRegistry meterRegistry;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody final BookRequest request) {
        meterRegistry.counter("books.created").increment();
        return new ResponseEntity<>(bookService.createBook(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable final Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable final Long id, @Valid @RequestBody final BookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable final Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}