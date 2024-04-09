package com.lucasramalho.passin.config;

import com.lucasramalho.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.lucasramalho.passin.domain.attendee.exceptions.AttendeeNotFindException;
import com.lucasramalho.passin.domain.checkin.exceptions.AttendeeAlreadyIsCheckedInException;
import com.lucasramalho.passin.domain.event.exceptions.EventIsFullException;
import com.lucasramalho.passin.domain.event.exceptions.EventNotFoundException;
import com.lucasramalho.passin.dto.exceptions.EventIsFullDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleEventNotFound(EventNotFoundException exception) {
        return new ResponseEntity<>("Evento não encontrado", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttendeeNotFindException.class)
    public ResponseEntity<String> handleAttendeeNotFound(AttendeeNotFindException exception) {
        return new ResponseEntity<>("Participante não encontrado", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity<String> handleAttendeeAlreadyExist(AttendeeAlreadyExistException exception) {
        return new ResponseEntity<>("Participante já cadastrado", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AttendeeAlreadyIsCheckedInException.class)
    public ResponseEntity<String> handleAttendeeAlreadyIsCheckedIn(AttendeeAlreadyIsCheckedInException exception) {
        return new ResponseEntity<>("Participante já fez check In", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EventIsFullException.class)
    public ResponseEntity<EventIsFullDTO> handleEventIsFull(EventIsFullException exception) {
        return new ResponseEntity<>(new EventIsFullDTO(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
