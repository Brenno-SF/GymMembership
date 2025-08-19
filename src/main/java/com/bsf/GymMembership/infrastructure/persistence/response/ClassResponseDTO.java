package com.bsf.GymMembership.infrastructure.persistence.response;

import java.time.LocalDateTime;

public record ClassResponseDTO(
        String classId,
        String name,
        String description,
        LocalDateTime dateHour
){}
