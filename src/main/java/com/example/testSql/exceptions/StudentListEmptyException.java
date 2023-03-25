package com.example.testSql.exceptions;

public class StudentListEmptyException extends RuntimeException {
    public StudentListEmptyException(String message) {
        super (message);
    }
}
