package com.example.testSql.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentFindMissingException extends RuntimeException {
    public StudentFindMissingException(String message) {
        super (message);
    }
}
