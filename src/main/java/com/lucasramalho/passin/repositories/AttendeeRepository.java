package com.lucasramalho.passin.repositories;

import com.lucasramalho.passin.domain.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
