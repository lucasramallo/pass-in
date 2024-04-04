package com.lucasramalho.passin.domain.checkin;

import com.lucasramalho.passin.domain.attendee.Attendee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_ins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checkin {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;
}
