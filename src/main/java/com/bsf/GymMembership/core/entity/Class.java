package com.bsf.GymMembership.core.entity;

import java.time.LocalDateTime;

public record Class(
        String classId,
        String name,
        String description,
        LocalDateTime dateHour
){}
