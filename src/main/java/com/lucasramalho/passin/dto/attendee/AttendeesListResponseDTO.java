package com.lucasramalho.passin.dto.attendee;

import lombok.Getter;

import java.util.List;

public record AttendeesListResponseDTO (List<AttendeeDetails> attendeeDetails){}

