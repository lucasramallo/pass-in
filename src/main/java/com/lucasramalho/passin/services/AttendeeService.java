package com.lucasramalho.passin.services;

import com.lucasramalho.passin.domain.attendee.Attendee;
import com.lucasramalho.passin.domain.checkin.Checkin;
import com.lucasramalho.passin.dto.attendee.AttendeeDetails;
import com.lucasramalho.passin.dto.attendee.AttendeesListResponseDTO;
import com.lucasramalho.passin.repositories.AttendeeRepository;
import com.lucasramalho.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {
    @Autowired
    private AttendeeRepository repository;
    @Autowired
    private CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.repository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkin = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkin.isPresent() ? checkin.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }
}
