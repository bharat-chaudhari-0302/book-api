package com.learning.book.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookRequest {
  @NotBlank(message = "Title is mandatory")
  @Size(max = 100, message = "Title must be less than 100 characters")
  private String title;

  @NotBlank(message = "Author is mandatory")
  @Size(max = 50, message = "Author must be less than 50 characters")
  private String author;

  @NotBlank(message = "ISBN is mandatory")
  @Pattern(regexp = "^\\d{10}|\\d{13}$", message = "ISBN must be 10 or 13 digits")
  private String isbn;

  @Positive(message = "Price must be positive")
  private double price;
}
