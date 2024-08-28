package com.unibuc.event.ticketing.exception;

public class EventNotFoundException extends Exception {
    public EventNotFoundException() {
    }

    public EventNotFoundException(String message) {
        super(message);
    }
}
