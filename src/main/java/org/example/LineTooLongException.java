package org.example;

public class LineTooLongException extends RuntimeException {
    public LineTooLongException(String message) {
        super(message);
    }
}
