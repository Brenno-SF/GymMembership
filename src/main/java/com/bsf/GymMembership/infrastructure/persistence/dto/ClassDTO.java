package com.bsf.GymMembership.infrastructure.persistence.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClassDTO(
        UUID classId,
        String name,
        String description,
        LocalDateTime dateHour
){}
