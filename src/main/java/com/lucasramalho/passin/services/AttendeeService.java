package com.lucasramalho.passin.services;

import com.lucasramalho.passin.domain.attendee.Attendee;
import com.lucasramalho.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.lucasramalho.passin.domain.attendee.exceptions.AttendeeNotFindException;
import com.lucasramalho.passin.domain.checkin.Checkin;
import com.lucasramalho.passin.dto.attendee.*;
import com.lucasramalho.passin.repositories.AttendeeRepository;
import com.lucasramalho.passin.repositories.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {
    @Autowired
    private AttendeeRepository repository;

    @Autowired
    private CheckinService checkinService;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.repository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkin = this.checkinService.getCheckIn(attendee.getId());
            LocalDateTime checkedInAt = checkin.isPresent() ? checkin.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        Optional<Attendee> isAttendeeRegistred = this.repository.findByEventIdAndEmail(eventId, email);

        if (isAttendeeRegistred.isPresent()) throw new AttendeeAlreadyExistException("Attendee is already registered");
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        this.repository.save(newAttendee);
        return newAttendee;
    }

    public void checkInAttendee(String attendeeId) {
        Attendee attendee = this.getAttendee(attendeeId);
        this.checkinService.registerCheckIn(attendee);
    }

    private Attendee getAttendee(String attendeeId) {
        return this.repository.findById(attendeeId).orElseThrow(() ->
                new AttendeeNotFindException("Attendee not find with id " + attendeeId)
        );
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        Attendee attendee = this.getAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/check-in").buildAndExpand(attendeeId).toUri().toString();

        AttendeeBadgeDTO attendeeBadgeDTO = new AttendeeBadgeDTO(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId());
        return new AttendeeBadgeResponseDTO(attendeeBadgeDTO);
    }
}
