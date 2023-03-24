package com.example.testSql.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectEmptyException extends RuntimeException{
    public ProjectEmptyException(String message){
        super (message);
    }
}
