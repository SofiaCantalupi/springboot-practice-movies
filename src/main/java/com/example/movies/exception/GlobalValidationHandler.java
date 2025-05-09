package com.example.movies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalValidationHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handlerBusinessException(BusinessException exc){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, exc.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DuplicatedMovieException.class)
    public ResponseEntity<ApiError> handlerDuplicatedMovie(DuplicatedMovieException exc){
        ApiError error = new ApiError(HttpStatus.CONFLICT, exc.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(InvalidYearException.class)
    public ResponseEntity<ApiError> handlerInvalidYear(InvalidYearException exc){
        ApiError error = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, exc.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException exc){
        Map<String, String> errors = exc.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (msg1, msg2) -> msg1
                ));

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, errors);
        return ResponseEntity.badRequest().body(error);
    }


}
