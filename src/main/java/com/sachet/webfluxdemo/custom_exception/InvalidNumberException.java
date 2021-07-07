package com.sachet.webfluxdemo.custom_exception;

public class InvalidNumberException extends Exception{

    public InvalidNumberException(String message) {
        super(message);
    }
}
