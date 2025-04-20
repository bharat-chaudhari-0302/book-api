package com.learning.book.api.dto;

import lombok.Data;

@Data
public class BookResponse {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private double price;
}
