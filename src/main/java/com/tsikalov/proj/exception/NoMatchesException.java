package com.tsikalov.proj.exception;

public class NoMatchesException extends RuntimeException {

    public NoMatchesException() {
    }

    public NoMatchesException(String message) {
        super(message);
    }
}
