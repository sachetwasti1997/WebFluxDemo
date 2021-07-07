package com.sachet.webfluxdemo.error_to_return;

import java.time.LocalDateTime;

public class ApiError {

    private LocalDateTime localDateTime = LocalDateTime.now();
    private String message;
    private Integer errorCode;
    private String url;

    public ApiError(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ApiError(String message, Integer errorCode, String url) {
        this.message = message;
        this.errorCode = errorCode;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
