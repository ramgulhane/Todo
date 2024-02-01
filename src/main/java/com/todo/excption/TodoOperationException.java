package com.todo.excption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TodoOperationException extends RuntimeException {
    public TodoOperationException(String message) {
        super(message);
    }
}
