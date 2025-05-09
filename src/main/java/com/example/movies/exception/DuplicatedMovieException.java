package com.example.movies.exception;

public class DuplicatedMovieException extends BusinessException{
    public DuplicatedMovieException(String message) {
        super(message);
    }
}
