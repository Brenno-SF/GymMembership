package com.bsf.GymMembership.core.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record GymClass(
        UUID classId,
        String name,
        String description,
        LocalDateTime dateHour
){}
