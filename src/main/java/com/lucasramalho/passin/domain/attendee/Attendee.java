package com.lucasramalho.passin.domain.attendee;

import com.lucasramalho.passin.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "attendees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
