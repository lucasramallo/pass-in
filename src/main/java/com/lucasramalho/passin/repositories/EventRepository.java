package com.lucasramalho.passin.repositories;

import com.lucasramalho.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {

}
