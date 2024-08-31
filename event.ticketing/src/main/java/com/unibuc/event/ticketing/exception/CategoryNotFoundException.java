package com.unibuc.event.ticketing.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
        super("Invalid category id provided");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
