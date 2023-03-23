package com.oleshko.weatherAnalyzer.exception;

public class NotConnectionException extends RuntimeException{

    public NotConnectionException(String message) {
        super(message);
    }

    public NotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotConnectionException(Throwable cause) {
        super(cause);
    }
}
