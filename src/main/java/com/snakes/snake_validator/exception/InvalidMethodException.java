package com.snakes.snake_validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class InvalidMethodException extends RuntimeException {
    public InvalidMethodException(String message) {
        super(message);
    }
}

