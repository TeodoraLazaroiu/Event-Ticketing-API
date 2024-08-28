package com.unibuc.event.ticketing.exception;

public class NotEnoughEventSeatsException extends Exception {
    public NotEnoughEventSeatsException() {
    }

    public NotEnoughEventSeatsException(String message) {
        super(message);
    }
}
