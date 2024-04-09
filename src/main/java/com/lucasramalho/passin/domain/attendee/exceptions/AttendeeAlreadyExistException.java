package com.lucasramalho.passin.domain.attendee.exceptions;

public class AttendeeAlreadyExistException extends RuntimeException {
    public AttendeeAlreadyExistException(String mensage) {
        super(mensage);
    }
}
