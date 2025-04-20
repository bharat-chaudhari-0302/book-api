package com.learning.book.api.mapper;

import com.learning.book.api.config.MapStructConfig;
import com.learning.book.api.dto.BookRequest;
import com.learning.book.api.dto.BookResponse;
import com.learning.book.api.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface BookMapper {
  @Mapping(target = "id", ignore = true)
  Book toEntity(BookRequest request);

  BookResponse toResponse(Book book);
}
