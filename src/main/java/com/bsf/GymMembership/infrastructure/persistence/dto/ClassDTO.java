package com.bsf.GymMembership.infrastructure.persistence.dto;

import java.time.LocalDateTime;

public record ClassDTO(
        String classId,
        String name,
        String description,
        LocalDateTime dateHour
){}
