package com.bsf.GymMembership.infrastructure.persistence.request;

import java.time.LocalDateTime;

public record ClassRequestDTO(
        String name,
        String description,
        LocalDateTime dateHour
){}
