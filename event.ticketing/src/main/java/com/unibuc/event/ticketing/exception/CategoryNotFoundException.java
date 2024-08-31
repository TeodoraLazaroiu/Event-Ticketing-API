package com.unibuc.event.ticketing.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
