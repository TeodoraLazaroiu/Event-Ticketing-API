package com.unibuc.event.ticketing.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
        super("Invalid password provided");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
