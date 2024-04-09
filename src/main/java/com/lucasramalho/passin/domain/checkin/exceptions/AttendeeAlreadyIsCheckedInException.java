package com.lucasramalho.passin.domain.checkin.exceptions;

public class AttendeeAlreadyIsCheckedInException extends RuntimeException {
    public AttendeeAlreadyIsCheckedInException(String message) {
        super(message);
    }

}
