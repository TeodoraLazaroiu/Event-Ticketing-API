package com.unibuc.event.ticketing.exception;

public class NotEnoughEventSeatsException extends Exception {
    public NotEnoughEventSeatsException() {
        super("Not enough seats available for this event");
    }

    public NotEnoughEventSeatsException(String message) {
        super(message);
    }
}
