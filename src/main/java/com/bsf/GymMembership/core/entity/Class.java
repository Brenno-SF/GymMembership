package com.bsf.GymMembership.core.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Class(
        UUID classId,
        String name,
        String description,
        LocalDateTime dateHour
){}
