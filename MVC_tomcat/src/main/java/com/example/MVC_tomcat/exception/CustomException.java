package com.example.MVC_tomcat.exception;

import java.io.IOException;

public class CustomException extends IOException {
    private static final long serialVersionUID = 1L;

    public CustomException(Throwable cause) { }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}