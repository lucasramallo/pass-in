package com.lucasramalho.passin.services;

import com.lucasramalho.passin.domain.attendee.Attendee;
import com.lucasramalho.passin.domain.checkin.Checkin;
import com.lucasramalho.passin.domain.checkin.exceptions.AttendeeAlreadyIsCheckedInException;
import com.lucasramalho.passin.repositories.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckinService {
    @Autowired
    private CheckinRepository checkinRepository;

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());

        Checkin newCheckin = new Checkin();
        newCheckin.setAttendee(attendee);
        newCheckin.setCreatedAt(LocalDateTime.now());

        this.checkinRepository.save(newCheckin);
    }

    private void verifyCheckInExists(String attendeeId) {
        Optional<Checkin> isCheckedIn = this.getCheckIn(attendeeId);

        if (isCheckedIn.isPresent()) throw new AttendeeAlreadyIsCheckedInException("Attendee already Checked In");
    }

    public Optional<Checkin> getCheckIn(String attendeeId) {
       return this.checkinRepository.findByAttendeeId(attendeeId);
    }
}
