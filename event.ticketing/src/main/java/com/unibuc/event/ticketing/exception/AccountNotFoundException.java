package com.unibuc.event.ticketing.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
