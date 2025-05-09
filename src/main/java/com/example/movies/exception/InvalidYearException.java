package com.example.movies.exception;

public class InvalidYearException extends BusinessException{
    public InvalidYearException(String message) {
        super(message);
    }
}
