package com.tsikalov.proj.exception;

public class HasDependenciesException extends RuntimeException {
    public HasDependenciesException() {
    }

    public HasDependenciesException(String message) {
        super(message);
    }
}
