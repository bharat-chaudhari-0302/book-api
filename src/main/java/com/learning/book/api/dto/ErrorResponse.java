package com.learning.book.api.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String details;
    private int status;
}
