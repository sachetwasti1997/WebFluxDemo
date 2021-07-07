package com.sachet.webfluxdemo.exception_handler;

import com.sachet.webfluxdemo.custom_exception.InvalidNumberException;
import com.sachet.webfluxdemo.dto.Response;
import com.sachet.webfluxdemo.error_to_return.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidNumberException.class)
    public ResponseEntity<?> handleInvalidNumberException(InvalidNumberException ex){
        ApiError apiError = new ApiError(ex.getMessage(), 400);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
